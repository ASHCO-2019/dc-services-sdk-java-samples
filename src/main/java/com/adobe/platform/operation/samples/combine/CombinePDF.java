/*
 * Copyright 2019 Adobe
 * All Rights Reserved.
 *
 * NOTICE: Adobe permits you to use, modify, and distribute this file in
 * accordance with the terms of the Adobe license agreement accompanying
 * it. If you have received this file from a source other than Adobe,
 * then your use, modification, or distribution of it requires the prior
 * written permission of Adobe.
 */

package com.adobe.platform.operation.samples.combine;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.platform.operation.ClientContext;
import com.adobe.platform.operation.exception.SdkException;
import com.adobe.platform.operation.exception.ServiceApiException;
import com.adobe.platform.operation.io.FileRef;
import com.adobe.platform.operation.pdfops.CombineFilesOperation;
import com.adobe.platform.operation.samples.exportpdf.ExportPDFToJPEG;

/**
 * This sample illustrates how to combine multiple PDF files into a single PDF file.
 * <p>
 * Note that the SDK supports combining upto 12 files in one operation.
 * <p>
 * Refer to README.md for instructions on how to run the samples.
 */
public class CombinePDF {

    // Initialize the logger.
    private static final Logger LOGGER = LoggerFactory.getLogger(ExportPDFToJPEG.class);


    public static void main(String[] args) {

        try {

            // Initial setup, create a default ClientContext and a new operation instance.
            ClientContext clientContext = ClientContext.createDefault();
            CombineFilesOperation combineFilesOperation = CombineFilesOperation.createNew();

            // Add operation input from source files.
            FileRef combineSource1 = FileRef.createFromLocalFile("src/main/resources/combineFilesInput1.pdf");
            FileRef combineSource2 = FileRef.createFromLocalFile("src/main/resources/combineFilesInput2.pdf");
            combineFilesOperation.addInput(combineSource1);
            combineFilesOperation.addInput(combineSource2);

            // Execute the operation.
            FileRef result = combineFilesOperation.execute(clientContext);

            // Save the result to the specified location.
            result.saveAs("output/combineFilesOutput.pdf");

        } catch (IOException | ServiceApiException | SdkException e) {
            LOGGER.error("Exception encountered while executing operation", e);
        }
    }
}
