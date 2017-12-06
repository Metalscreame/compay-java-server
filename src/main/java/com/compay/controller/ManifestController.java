package com.compay.controller;

import com.jcabi.manifests.Manifests;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping("/manifest")
class ManifestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ManifestController.class);

    private static final String CHARSET = "charset=UTF-8";
    private static final String HEADER_SEPARATOR = ";";
    private static final String LINE_SEPARATOR = System.getProperty("line.separator");
    private static final Map<String, String> MANIFEST_DATA = new LinkedHashMap<>();
    private static final String STRING_MANIFEST_DATA;

    static {
        final StringBuilder stringManifestBuilder = new StringBuilder();
        final String classesPath = ManifestController.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        try {
            File projectRootPath = new File(classesPath);
            projectRootPath = projectRootPath.getParentFile();
            projectRootPath = projectRootPath.getParentFile();

            final String manifestPath = projectRootPath.getPath()
                    + File.separator
                    + "META-INF"
                    + File.separator
                    + "MANIFEST.MF";

            final Properties prop = new Properties();

            try (final InputStream inputStream = new FileInputStream(manifestPath)) {
                prop.load(inputStream);
            }

            prop.forEach((key, value) -> {
                MANIFEST_DATA.put((String) key, (String) value);
                stringManifestBuilder.append(key).append(": ").append(value).append(LINE_SEPARATOR);
            });
        } catch (IOException ex) {
            LOGGER.warn("Error while reading manifest value: " + ex.getMessage());
        }
        Manifests.DEFAULT.forEach((key, value) -> {
            MANIFEST_DATA.put(key, value);
            stringManifestBuilder.append(key).append(": ").append(value).append(LINE_SEPARATOR);
        });
        STRING_MANIFEST_DATA = stringManifestBuilder.toString();
    }

    @RequestMapping(value = "/data", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE + HEADER_SEPARATOR + CHARSET)
    @ResponseBody
    private String getAllData() {
        return STRING_MANIFEST_DATA;
    }

    @RequestMapping(value = "/data/{key}", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE + HEADER_SEPARATOR + CHARSET)
    @ResponseBody
    private String getDataByKey(@PathVariable String key) {
        return MANIFEST_DATA.getOrDefault(key, "Requested key not found!");
    }
}
