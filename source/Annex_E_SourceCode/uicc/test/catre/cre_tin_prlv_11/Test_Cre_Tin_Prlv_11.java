//-----------------------------------------------------------------------------
//    Package Definition
//-----------------------------------------------------------------------------
package uicc.test.catre.cre_tin_prlv_11;

//-----------------------------------------------------------------------------
//  Imports
//-----------------------------------------------------------------------------
import org.etsi.scp.wg3.uicc.jcapi.userinterface.*;
import org.etsi.scp.wg3.uicc.jcapi.userclass.*;

public class Test_Cre_Tin_Prlv_11 extends UiccTestModel {

    static final String CAP_FILE_PATH = "uicc/test/catre/cre_tin_prlv_11";
    static final String CLASS_AID_1 = "A0000000 090005FF FFFFFF89 50010001";
    static final String APPLET_AID_1 = "A0000000 090005FF FFFFFF89 50010102";
    static final String APPLET_AID_2 = "A0000000 090005FF FFFFFF89 50010202";
    static final String APPLET_AID_3 = "A0000000 090005FF FFFFFF89 50010302";
    static final String APPLET_AID_4 = "A0000000 090005FF FFFFFF89 50010402";
   
    private UiccAPITestCardService test;
    APDUResponse response;
      
          
    public Test_Cre_Tin_Prlv_11() {
        test = UiccAPITestCardService.getTheUiccTestCardService();
    }
    
    public boolean run() {
        APDUResponse data = null;
        initialiseResults();
        
        // test script
        test.reset();
        test.terminalProfileSession(UiccCardManagementService.DEFAULT_TERMINAL_PROFILE);

        // Install packages
        test.loadPackage(CAP_FILE_PATH);
        
        /*********************************************************************/
        /** Testcase 1                                                       */
        /*********************************************************************/

        // Install Applet1
        test.installApplet(CAP_FILE_PATH, CLASS_AID_1, APPLET_AID_1, 
                           "8008" + // TLV UICC Toolkit application specific parameters
                               "01" +   // V Priority Level
                               "00" +   // V Max. number of timers
                               "0A" +   // V Maximum text length for a menu entry
                               "00" +   // V Maximum number of menu entries
                               "00" +   // V Maximum number of channels 
                               "00" +   // LV Minimum Security Level field
                               "00" +   // LV TAR Value(s) 
                               "00");   // V Maximum number of services
        
        // Install Applet2
        test.installApplet(CAP_FILE_PATH, CLASS_AID_1, APPLET_AID_2, 
                           "8008" + // TLV UICC Toolkit application specific parameters
                               "01" +   // V Priority Level
                               "00" +   // V Max. number of timers
                               "0A" +   // V Maximum text length for a menu entry
                               "00" +   // V Maximum number of menu entries
                               "00" +   // V Maximum number of channels 
                               "00" +   // LV Minimum Security Level field
                               "00" +   // LV TAR Value(s) 
                               "00");   // V Maximum number of services
                
        // Install Applet3
        test.installApplet(CAP_FILE_PATH, CLASS_AID_1, APPLET_AID_3, 
                           "8008" + // TLV UICC Toolkit application specific parameters
                               "01" +   // V Priority Level
                               "00" +   // V Max. number of timers
                               "0A" +   // V Maximum text length for a menu entry
                               "00" +   // V Maximum number of menu entries
                               "00" +   // V Maximum number of channels 
                               "00" +   // LV Minimum Security Level field
                               "00" +   // LV TAR Value(s) 
                               "00");   // V Maximum number of services
                
        // Install Applet4
        test.installApplet(CAP_FILE_PATH, CLASS_AID_1, APPLET_AID_4, 
                           "8008" + // TLV UICC Toolkit application specific parameters
                               "01" +   // V Priority Level
                               "00" +   // V Max. number of timers
                               "0A" +   // V Maximum text length for a menu entry
                               "00" +   // V Maximum number of menu entries
                               "00" +   // V Maximum number of channels 
                               "00" +   // LV Minimum Security Level field
                               "00" +   // LV TAR Value(s) 
                               "00");   // V Maximum number of services
                

         // Card Initialisation
        test.reset();
        test.terminalProfileSession("09030020 21");
        
        // Trigger the applets
        response = test.envelopeEventDownloadUserActivity();
        addResult(response.checkSw("9000"));
        

        /*********************************************************************/
        /*********************************************************************/
        /** Check Applets                                                    */
        /*********************************************************************/
        /*********************************************************************/

        response = test.selectApplication(APPLET_AID_1);
        addResult(response.checkData("10" + APPLET_AID_1 + "01" + "CC"));
        response = test.selectApplication(APPLET_AID_2);
        addResult(response.checkData("10" + APPLET_AID_2 + "01" + "CC"));
        response = test.selectApplication(APPLET_AID_3);
        addResult(response.checkData("10" + APPLET_AID_3 + "01" + "CC"));
        response = test.selectApplication(APPLET_AID_4);
        addResult(response.checkData("10" + APPLET_AID_4 + "01" + "CC"));


        // Card Initialisation
        test.reset();
        test.terminalProfileSession("09030020 21");
        // delete Applet4
        test.deleteApplet(APPLET_AID_4);
        
        // Card Initialisation
        test.reset();
        test.terminalProfileSession("09030020 21");
        // Trigger the applets
        response = test.envelopeEventDownloadUserActivity();
        addResult(response.checkSw("9000"));
        

        /*********************************************************************/
        /*********************************************************************/
        /** Check Applets                                                    */
        /*********************************************************************/
        /*********************************************************************/

        response = test.selectApplication(APPLET_AID_1);
        addResult(response.checkData("10" + APPLET_AID_1 + "02" + "CCCC"));
        response = test.selectApplication(APPLET_AID_2);
        addResult(response.checkData("10" + APPLET_AID_2 + "02" + "CCCC"));
        response = test.selectApplication(APPLET_AID_3);
        addResult(response.checkData("10" + APPLET_AID_3 + "02" + "CCCC"));


        // Card Initialisation
        test.reset();
        test.terminalProfileSession("09030020 21");
        // delete Applet4
        test.deleteApplet(APPLET_AID_3);
        
        // Card Initialisation
        test.reset();
        test.terminalProfileSession("09030020 21");
        // Trigger the applets
        response = test.envelopeEventDownloadUserActivity();
        addResult(response.checkSw("9000"));
        

        /*********************************************************************/
        /*********************************************************************/
        /** Check Applets                                                    */
        /*********************************************************************/
        /*********************************************************************/

        response = test.selectApplication(APPLET_AID_1);
        addResult(response.checkData("10" + APPLET_AID_1 + "03" + "CCCCCC"));
        response = test.selectApplication(APPLET_AID_2);
        addResult(response.checkData("10" + APPLET_AID_2 + "03" + "CCCCCC"));


        /*********************************************************************/
        /*********************************************************************/
        /** Restore  card                                                    */
        /*********************************************************************/
        /*********************************************************************/

        test.reset();
        test.terminalProfileSession(UiccCardManagementService.DEFAULT_TERMINAL_PROFILE);
        // delete applets and package
        test.deleteApplet(APPLET_AID_1);
        test.deleteApplet(APPLET_AID_2);
        test.deletePackage(CAP_FILE_PATH);
        
        
        return getOverallResult();
    }
}   
