package com.avsQual.testcases;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import com.avsQual.utility.Cal_Qual_VerUtility;

public class testrunner {

    static TestNG testng;

    public static void main(String[] args) throws Exception {

        // ---------------------------------------------------------
        // 1. WinAppDriver confirmation
        // ---------------------------------------------------------
        int option = JOptionPane.showConfirmDialog(
                null,
                "Did you launch the WinAppDriver.exe? " + "\r\n"
                + "Continue by clicking Yes, else click No to stop the process and relaunch WinAppDriver.exe."
                + "\r\n"
                + "One can find it at the location- C:\\Program Files (x86)\\Windows Application Driver.",
                "Alert!!!",
                JOptionPane.YES_NO_OPTION);

        if (option == 1) {
            System.exit(1);
        }

        // ---------------------------------------------------------
        // 2. Excel information popup
        // ---------------------------------------------------------
        JOptionPane.showMessageDialog(
                null,
                "For App UID & PW, which Asset & Setup Name to select, "
                + "BaseStation IP address, SOP data, Study time in Minutes & Study save comment, "
                + "Please update the excel sheet provided with this exe",
                "InfoBox: Alert!",
                JOptionPane.INFORMATION_MESSAGE);

        // ---------------------------------------------------------
        // 3. Read Excel data
        // ---------------------------------------------------------
        Object[][] excelData =
                Cal_Qual_VerUtility.getTestData("CQV");

        // ---------------------------------------------------------
        // 4. Create TestNG Suite
        // ---------------------------------------------------------
        XmlSuite suite = new XmlSuite();
        suite.setName("CQV Suite");
        suite.setParallel(XmlSuite.ParallelMode.NONE);

        // ---------------------------------------------------------
        // 5. Create ONE XmlTest for EACH Excel row
        // ---------------------------------------------------------
        for (int i = 0; i < excelData.length; i++) {

            Object[] row = excelData[i];

            XmlTest xmlTest = new XmlTest(suite);

            xmlTest.setName("Iteration " + (i + 1));

            // Execute tests in the order defined by priority
            xmlTest.setPreserveOrder(true);

            // -----------------------------------------------------
            // Excel row → TestNG parameters
            // -----------------------------------------------------
            Map<String, String> parameters =
                    new LinkedHashMap<>();

            parameters.put(
                    "AssetName",
                    row.length > 0 ? String.valueOf(row[0]) : "");

            parameters.put(
                    "SetupName",
                    row.length > 1 ? String.valueOf(row[1]) : "");

            parameters.put(
                    "AVS_IP",
                    row.length > 2 ? String.valueOf(row[2]) : "");

            parameters.put(
                    "SelectAVS",
                    row.length > 3 ? String.valueOf(row[3]) : "");

            parameters.put(
                    "BathTemp",
                    row.length > 4 ? String.valueOf(row[4]) : "");

            xmlTest.setParameters(parameters);

            // -----------------------------------------------------
            // Run Qual_eventsTestF for this Excel row
            // -----------------------------------------------------
            xmlTest.setXmlClasses(
                    Collections.singletonList(
                            new XmlClass(Qual_eventsTestF.class.getName()
                            )
                    )
            );
        }

        // ---------------------------------------------------------
        // 6. Start TestNG
        // ---------------------------------------------------------
        testng = new TestNG();

        testng.setXmlSuites(
                Collections.singletonList(suite)
        );

        testng.setVerbose(1);

        testng.run();
    }
}