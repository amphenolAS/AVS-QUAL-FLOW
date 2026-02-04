package com.avsQual.runner;

import org.testng.TestNG;
import java.io.*;
import java.util.Collections;
import java.util.List;

public class TestRunner {
    public static void main(String[] args) {
        System.out.println("Starting TestNG from TestRunner...");

        try {
            // Load testng.xml from classpath (src/main/resources/testng.xml)
            InputStream in = TestRunner.class.getResourceAsStream("/testng.xml");
            if (in == null) {
                System.err.println("ERROR: testng.xml not found on classpath.");
                System.exit(1);
            }

            // write resource to temp file because TestNG expects a filesystem path
            File tmp = File.createTempFile("testng-", ".xml");
            tmp.deleteOnExit();
            try (FileOutputStream out = new FileOutputStream(tmp)) {
                byte[] buf = new byte[8192];
                int r;
                while ((r = in.read(buf)) != -1) out.write(buf, 0, r);
            }

            TestNG testng = new TestNG();
            List<String> suites = Collections.singletonList(tmp.getAbsolutePath());
            testng.setTestSuites(suites);

            // Optionally pass args through if user provided a different xml path
            if (args.length > 0) {
                // e.g. java -jar app.jar path/to/other.xml
                testng.setTestSuites(Collections.singletonList(args[0]));
            }

            testng.run();
            System.out.println("TestNG run finished.");
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(2);
        }
    }
}
