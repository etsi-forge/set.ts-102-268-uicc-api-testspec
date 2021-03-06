//-----------------------------------------------------------------------------
//Test_Api_2_Prh_Gtil.java
//-----------------------------------------------------------------------------

//-----------------------------------------------------------------------------
//Package Definition
//-----------------------------------------------------------------------------
package uicc.test.toolkit.api_2_prh_gtil;

import org.etsi.scp.wg3.uicc.jcapi.userclass.*;
import org.etsi.scp.wg3.uicc.jcapi.userinterface.*;

//-----------------------------------------------------------------------------
//Imports
//-----------------------------------------------------------------------------

/**
 * Test Area : uicc.test.toolkit.api_2_prh_gtil
 *
 * @version 0.0.1 - 23 juin 2005
 * @author 3GPP T3 SWG API
 */
public class Test_Api_2_Prh_Gtil extends UiccTestModel
{
    static final String CAP_FILE_PATH = "uicc/test/toolkit/api_2_prh_gtil";
    static final String CLASS_AID_1 = "A0000000 090005FF FFFFFF89 20010001";
    static final String APPLET_AID_1 = "A0000000 090005FF FFFFFF89 20010102";
   
    private UiccAPITestCardService test;
    APDUResponse response;
          
    public Test_Api_2_Prh_Gtil() {
        test = UiccAPITestCardService.getTheUiccTestCardService();
    }
    
    public boolean run() {
        APDUResponse data = null;
        initialiseResults();
        
        // test script
        test.reset();
        test.terminalProfileSession(UiccCardManagementService.DEFAULT_TERMINAL_PROFILE);
        
        /*********************************************************************/
        /** Testcase 0                                                       */
        /*********************************************************************/

        // Install package
        test.loadPackage(CAP_FILE_PATH);
        // Install Applet1
        test.installApplet(CAP_FILE_PATH, CLASS_AID_1, APPLET_AID_1, 
                           "8008" + // TLV UICC Toolkit application specific parameters
                           "01" +   // V Priority Level
                           "00" +   // V Max. number of timers
                           "00" +   // V Maximum text length for a menu entry
                           "00" +   // V Maximum number of menu entries
                           "01" +   // V Maximum number of channels 
                           "00" +   // LV Minimum Security Level field
                           "00" +   // LV TAR Value(s) 
                           "00" +   // V Maximum number of services
                           "8104" + // TLV UICC Access application specific parameters
                           "00" +   // LV UICC File System AID field
                           "0100" + // LV Access Domain for UICC file system = ALWAYS
                           "00" );  // LV Access Domain DAP field       
        

        // Card Initialisation
        test.reset();
        test.terminalProfileSession(UiccCardManagementService.DEFAULT_TERMINAL_PROFILE);
        
        /*********************************************************************/   
        /** Testcase 1 & 2                                                   */   
        /*********************************************************************/   
                                                                                  
        response = test.unrecognizedEnvelope();                                   
        addResult(response.checkSw("9112"));
                                                                                  
        // Fetch the proactive command                                            
        response = test.fetch("12");                                              
        addResult(response.checkData("D0108103 01210082 0281028D 05045465"
                                   + "7874"));

        // Terminal response with no additional information
        response = test.terminalResponse("81030121 00020282 81030100");  
        addResult(response.checkSw("9112"));
                                                                          

        /*********************************************************************/   
        /** Testcase 3 & 4                                                   */   
        /*********************************************************************/   
                                                                                  
        // Fetch the proactive command                                            
        response = test.fetch("12");                                              
        addResult(response.checkData("D0108103 01210082 0281028D 05045465"
                                   + "7874"));

        // Terminal response with 1 additional byte
        response = test.terminalResponse("81030121 00020282 81030202 55");  
        addResult(response.checkSw("9112"));
                                                                          
        /*********************************************************************/   
        /** Testcase 5 & 6                                                   */   
        /*********************************************************************/   
                                                                                  
        // Fetch the proactive command                                            
        response = test.fetch("12");                                              
        addResult(response.checkData("D0108103 01210082 0281028D 05045465"
                                   + "7874"));

        // Terminal response with 7Eh additional bytes
        response = test.terminalResponse("81030121 00020282 81037F02 55555555"
                                       + "55555555 55555555 55555555 55555555"
                                       + "55555555 55555555 55555555 55555555"
                                       + "55555555 55555555 55555555 55555555"
                                       + "55555555 55555555 55555555 55555555"
                                       + "55555555 55555555 55555555 55555555"
                                       + "55555555 55555555 55555555 55555555"
                                       + "55555555 55555555 55555555 55555555"
                                       + "55555555 55555555 5555");  
        addResult(response.checkSw("9112"));

        /*********************************************************************/   
        /** Testcase 7 & 8                                                   */   
        /*********************************************************************/   
                                                                                  
        // Fetch the proactive command                                            
        response = test.fetch("12");                                              
        addResult(response.checkData("D0108103 01210082 0281028D 05045465"
                                   + "7874"));

        // Terminal response with 7Fh additional bytes
        response = test.terminalResponse("81030121 00020282 81038180 02555555"
                                       + "55555555 55555555 55555555 55555555"
                                       + "55555555 55555555 55555555 55555555"
                                       + "55555555 55555555 55555555 55555555"
                                       + "55555555 55555555 55555555 55555555"
                                       + "55555555 55555555 55555555 55555555"
                                       + "55555555 55555555 55555555 55555555"
                                       + "55555555 55555555 55555555 55555555"
                                       + "55555555 55555555 55555555");  
        addResult(response.checkSw("9112"));

        /*********************************************************************/   
        /** Testcase 9 & 10                                                  */   
        /*********************************************************************/   
                                                                                  
        // Fetch the proactive command                                            
        response = test.fetch("12");                                              
        addResult(response.checkData("D0108103 01210082 0281028D 05045465"
                                   + "7874"));

        // Terminal response with 80h additional bytes
        response = test.terminalResponse("81030121 00020282 81038181 02555555"
                                       + "55555555 55555555 55555555 55555555"
                                       + "55555555 55555555 55555555 55555555"
                                       + "55555555 55555555 55555555 55555555"
                                       + "55555555 55555555 55555555 55555555"
                                       + "55555555 55555555 55555555 55555555"
                                       + "55555555 55555555 55555555 55555555"
                                       + "55555555 55555555 55555555 55555555"
                                       + "55555555 55555555 55555555 55");  
        addResult(response.checkSw("9112"));

        /*********************************************************************/   
        /** Testcase 11 & 12                                                 */   
        /*********************************************************************/   
                                                                                  
        // Fetch the proactive command                                            
        response = test.fetch("12");                                              
        addResult(response.checkData("D0108103 01210082 0281028D 05045465"
                                   + "7874"));

        // Terminal response with F2h additional bytes
        response = test.terminalResponse("81030121 00020282 810381F3 02555555"
                                       + "55555555 55555555 55555555 55555555"
                                       + "55555555 55555555 55555555 55555555"
                                       + "55555555 55555555 55555555 55555555"
                                       + "55555555 55555555 55555555 55555555"
                                       + "55555555 55555555 55555555 55555555"
                                       + "55555555 55555555 55555555 55555555"
                                       + "55555555 55555555 55555555 55555555"
                                       + "55555555 55555555 55555555 55555555"
                                       + "55555555 55555555 55555555 55555555"
                                       + "55555555 55555555 55555555 55555555"
                                       + "55555555 55555555 55555555 55555555"
                                       + "55555555 55555555 55555555 55555555"
                                       + "55555555 55555555 55555555 55555555"
                                       + "55555555 55555555 55555555 55555555"
                                       + "55555555 55555555 55555555 555555");  
        addResult(response.checkSw("9112"));

        /*********************************************************************/   
        /** Testcase 13 & 14                                                 */   
        /*********************************************************************/   
                                                                                  
        // Fetch the proactive command                                            
        response = test.fetch("12");                                              
        addResult(response.checkData("D0108103 01210082 0281028D 05045465"
                                   + "7874"));

        // Terminal response with 2 result TLVs
        response = test.terminalResponse("81030121 00020282 81030302 01230301"
                                       + "00");  
        addResult(response.checkSw("9112"));

        /*********************************************************************/   
        /** Testcase 15                                                      */   
        /*********************************************************************/   
                                                                                  
        // Fetch the proactive command                                            
        response = test.fetch("12");                                              
        addResult(response.checkData("D0108103 01210082 0281028D 05045465"
                                   + "7874"));

        // Terminal response with no result TLV
        response = test.terminalResponse("81030121 00020282 81");  
        addResult(response.checkSw("9000"));

        /*********************************************************************/
        /*********************************************************************/
        /** Check Applets                                                    */
        /*********************************************************************/
        /*********************************************************************/

        response = test.selectApplication(APPLET_AID_1);
        addResult(response.checkData("10" + APPLET_AID_1
                                   + "0FCCCCCC CCCCCCCC CCCCCCCC CCCCCCCC"));
        
        /*********************************************************************/
        /*********************************************************************/
        /** Restore  card                                                    */
        /*********************************************************************/
        /*********************************************************************/
        test.reset();
        test.terminalProfileSession(UiccCardManagementService.DEFAULT_TERMINAL_PROFILE);
        
        // delete applets and package
        test.deleteApplet(APPLET_AID_1);
        test.deletePackage(CAP_FILE_PATH);
        
        return getOverallResult();
    }
}
