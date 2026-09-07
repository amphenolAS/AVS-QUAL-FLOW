package com.avsQual.testcases;

import java.io.IOException;

import org.testng.annotations.Factory;

import com.avsQual.utility.Cal_Qual_VerUtility;

public class Qual_eventsTestFactory {


    @Factory(
            dataProvider = "CQV",
            dataProviderClass = Cal_Qual_VerUtility.class
    )
    public Object[] createInstances(
            String AssetName,
            String SetupName,
            String AVS_IP,
            String SelectAVS,
            String BathTemp) throws IOException {


        return new Object[] { new Qual_eventsTestF(
                       /* AssetName,
                        SetupName,
                        AVS_IP,
                        SelectAVS,
                        BathTemp*/
                )

        };
    }
}