package ru.nsu.upprpo.pianogame.model.data.game.read;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZIPReader {

    private static final Logger logger = LogManager.getLogger(ZIPReader.class);

    protected final Map<String, DataReader> readerStructure;

    protected DataReader defaultReader;

    public ZIPReader(@NotNull Map<String, DataReader> structure, @NotNull DataReader defaultReader) {
        this.readerStructure = structure;
        this.defaultReader = defaultReader;
    }

    public ZIPReader(@NotNull Map<String, DataReader> structure) {
        this.readerStructure = structure;
        this.defaultReader = InputStream::readAllBytes;
    }

    public RawDataContainer readZIP(Path path) throws IOException {
        var builder = RawDataContainer.builder();
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        md.reset();
        try (InputStream fis = Files.newInputStream(path);
             DigestInputStream dis = new DigestInputStream(fis, md);
             ZipInputStream zis = new ZipInputStream(dis)) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                String name = entry.getName();
                var func = readerStructure.get(name);
                if (func != null) {
                    try {
                        builder.withObject(name, func.read(new FilterInputStream(zis) {
                            @Override
                            public void close() throws IOException {
                                zis.closeEntry();
                            }
                        }));
                    } catch (Throwable t) {
                        logger.warn(() -> String.format("Error while read entry %s in %s", name, path), t);
                    }
                } else {
                    try {
                        builder.withObject(name, defaultReader.read(zis));
                    } catch (Throwable t) {
                        logger.warn(() -> String.format("Error while read entry %s in %s", name, path), t);
                    }
                }
            }
            dis.readAllBytes();
        }
        return builder.withHash(HexFormat.of().formatHex(md.digest())).build();
    }


    @FunctionalInterface
    public interface DataReader {
        Object read(InputStream is) throws Throwable;

    }

}