package io.swen90007sm2.framework.common.util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * helper to print banner
 *
 * @author xiaotian
 */
public class BannerUtil {
    public static final String CUSTOM_BANNER_NAME = "banner.txt";

    public static void printBanner() {
        URL url = Thread.currentThread().getContextClassLoader().getResource(CUSTOM_BANNER_NAME);
        if (url != null) {
            try {
                Path path = Paths.get(url.toURI());
                try (Stream<String> stream = Files.lines(path)) {
                    stream.forEach(System.out::println);
                }
            } catch (URISyntaxException | IOException e) {
                printDefaultBanner();
            }
        } else {
            printDefaultBanner();
        }
    }

    private static void printDefaultBanner() {
        System.out.println("  ████████ ██       ██ ████████ ████     ██  ████   ████   ████   ████  ██████\n" +
                " ██░░░░░░ ░██      ░██░██░░░░░ ░██░██   ░██ █░░░ █ █░░░██ █░░░██ █░░░██░░░░░░█\n" +
                "░██       ░██   █  ░██░██      ░██░░██  ░██░█   ░█░█  █░█░█  █░█░█  █░█     ░█\n" +
                "░█████████░██  ███ ░██░███████ ░██ ░░██ ░██░ ████ ░█ █ ░█░█ █ ░█░█ █ ░█     █ \n" +
                "░░░░░░░░██░██ ██░██░██░██░░░░  ░██  ░░██░██ ░░░█  ░██  ░█░██  ░█░██  ░█    █  \n" +
                "       ░██░████ ░░████░██      ░██   ░░████   █   ░█   ░█░█   ░█░█   ░█   █   \n" +
                " ████████ ░██░   ░░░██░████████░██    ░░███  █    ░ ████ ░ ████ ░ ████   █    \n" +
                "░░░░░░░░  ░░       ░░ ░░░░░░░░ ░░      ░░░  ░      ░░░░   ░░░░   ░░░░   ░     \n");
    }
}
