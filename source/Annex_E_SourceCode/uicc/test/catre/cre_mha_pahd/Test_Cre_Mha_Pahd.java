//-----------------------------------------------------------------------------
//Test_Cre_Mha_Pahd
//-----------------------------------------------------------------------------

//-----------------------------------------------------------------------------
//Package Definition
//-----------------------------------------------------------------------------
package uicc.test.catre.cre_mha_pahd;

//-----------------------------------------------------------------------------
//Imports
//-----------------------------------------------------------------------------


import org.etsi.scp.wg3.uicc.jcapi.userclass.UiccAPITestCardService;
import org.etsi.scp.wg3.uicc.jcapi.userclass.UiccTestModel;
import org.etsi.scp.wg3.uicc.jcapi.userinterface.APDUResponse;
import org.etsi.scp.wg3.uicc.jcapi.userinterface.UiccCardManagementService;


public class Test_Cre_Mha_Pahd extends UiccTestModel {
	/** relative path of the package */
	private static String			CAP_FILE_PATH		= "uicc/test/catre/cre_mha_pahd";
	/** test applet 1 class AID */
	private static String			CLASS_AID_1			= "A0000000 090005FF FFFFFF89 50010001";
	/** test applet 1 instance aid */
	private static String			APPLET_AID_1		= "A0000000 090005FF FFFFFF89 50010102";
	/** test applet 1 class AID */
	private static String			CLASS_AID_2			= "A0000000 090005FF FFFFFF89 50020001";
	/** test applet 1 instance aid */
	private static String			APPLET_AID_2		= "A0000000 090005FF FFFFFF89 50020102";
	/** */
	private static String			CLASS_AID_3			= "A0000000 090005FF FFFFFF89 50030001";
	/** test applet 1 instance aid */
	private static String			APPLET_AID_3		= "A0000000 090005FF FFFFFF89 50030102";
	/** */
	private static String			TERMINAL_PROFILE	= "09030120 00000000 00000000 F0";
	/**  */
	private UiccAPITestCardService	test				= null;
	/** contains the response from the executed command */
	private APDUResponse			response			= null;
	/** stores the test result */
	private boolean					testresult			= false;

    public final byte[] AID_ADF1 = {(byte)0xA0, (byte)0x00, (byte)0x00, (byte)0x00,
    (byte)0x09, (byte)0x00, (byte)0x05, (byte)0xFF,
    (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0x89,
    (byte)0xE0, (byte)0x00, (byte)0x00, (byte)0x02};



	/**
	 *
	 */
	public Test_Cre_Mha_Pahd(){
		test = UiccAPITestCardService.getTheUiccTestCardService();
	}
	/**
	 * Installs the applet, runs the tests and checks the test result.
	 */
	public boolean run(){
		// test script
        test.reset();
        test.terminalProfileSession("0301");

        // Install Applet
        test.loadPackage(CAP_FILE_PATH);
        test.installApplet(CAP_FILE_PATH, CLASS_AID_1, APPLET_AID_1,
                            "800C" + // TLV UICC Toolkit application specific parameters
                            "01" +   // V Priority Level
                            "08" +   // V Max. number of timers
                            "20" +   // V Maximum text length for a menu entry
                            "02" +   // V Maximum number of menu entries
                            "01010202" + // V Pos./Id. of menu entries
                            "01" +   // V Maximum number of channels
                            "00" +   // LV Minimum Security Level field
                            "00" +   // LV TAR Value(s)
                            "01" );  // V Maximum number of services


        test.installApplet(CAP_FILE_PATH, CLASS_AID_2, APPLET_AID_2,
                            "800C" + // TLV UICC Toolkit application specific parameters
                            "02" +   // V Priority Level
                            "01" +   // V Max. number of timers
                            "20" +   // V Maximum text length for a menu entry
                            "02" +   // V Maximum number of menu entries
                            "03030404" + // V Pos./Id. of menu entries
                            "00" +   // V Maximum number of channels
                            "00" +   // LV Minimum Security Level field
                            "00" +   // LV TAR Value(s)
                            "01" );  // V Maximum number of services

        test.reset();
        //TC 1 - select MF
        response = test.selectFile("3F00");
        //TC 2 - send Terminal Profile except SET_EVENT_LIST,POLL_INTERVALL,SETUP_IDLE_MODE_TEXT,SET_UP_MENU
        response = test.terminalProfileSession("FFFFDFDF FEFF1FEF FF0000FF FF9FFFEF" +
                                               "03FF0000 007FE300 01");
        //TC 3 - initialization with all the facilities supported (without SETUP_EVENT_LIST
        response = test.terminalProfileSession("FFFFFFFF FEFFFFFF FFFFFFFF FFFFFFFF " +
        		                       "FFFFFFFF FFFFFFFF FFFFFFFF FFFFFFFF " +
        		                       "FFFF");
        response = test.envelopeMenuSelection("900102","9500");
        testresult = response.checkSw("9000");
        //TC 4
        response = test.envelopeMenuSelection("900101","");
        testresult &= response.checkSw("9000");
        //TC 5
        response = test.envelopeTimerExpiration("A40101");
        //TC 6
        response = test.envelopeCallControlByNAA();
        testresult &= response.checkSw("9000");
        //TC 7
        response = test.envelopeEventDownloadMTCall();
        testresult &= response.checkSw("9000");
        //TC 8
        response = test.envelopeEventDownloadCallConnected();
        testresult &= response.checkSw("9000");
        //TC 9
        response = test.envelopeEventDownloadCallDisconnected();
        testresult &= response.checkSw("9000");
        //TC 10
        response = test.envelopeEventDownloadLocationStatus();
        testresult &= response.checkSw("9000");
        //TC 11
        response = test.envelopeEventDownloadUserActivity();
        testresult &= response.checkSw("9000");
        //TC 12
        response = test.envelopeEventDownloadIdleScreenAvailable();
        testresult &= response.checkSw("9000");
        //TC 13
        response = test.envelopeEventDownloadCardReaderStatus();
        testresult &= response.checkSw("9000");
        //TC 14
        response = test.envelopeEventDownloadLanguageSelection();
        testresult &= response.checkSw("9000");
        //TC 15
        response = test.envelopeEventDownloadBrowserTermination();
        testresult &= response.checkSw("9000");
        //TC 16
        response = test.status("00","0C","00");
        testresult &= response.checkSw("911A");
        //TC 17
        response = test.fetch("1A");
        response = test.terminalResponse("81030140 01820282 8183010038 0281003502 " +
        		                         "03003902 000A");
        testresult &= response.checkSw("9000");
        response = test.envelopeEventDownloadDataAvailable("B8028100");
        testresult &= response.checkSw("9000");
        //TC 18
        response = test.envelopeEventDownloadChannelStatus("B8028100");
        testresult &= response.checkSw("9000");
        //TC 19
        response = test.unrecognizedEnvelope();
        testresult &= response.checkSw("9000");
        //TC 20
        response = test.envelopeEventDownloadAccessTechnologyChange();
        testresult &= response.checkSw("9000");
        //TC 21
        response = test.envelopeEventDownloadDisplayParametersChanged();
        //fetch declare service
        response = test.fetch("10");
        //get the service record tlv from declare service command
        String servrectlv = getServiceRecordTLV(response.getData());
        response = test.terminalResponse("81030147 0082028281 830100");
        testresult &= response.checkSw("9000");
        //TC 22
        response = test.envelopeEventDownloadLocalConnection(servrectlv);
        testresult &= response.checkSw("9000");
        //TC 23
        response = test.envelopeEventDownloadCallConnected();
        //fetch display text
        response = test.fetch("14");
        response = test.terminalResponse("81030121 80820282 81030100");
        testresult &= response.checkSw("9000");
        //TC 24
        //- Select for activation ADF1
        response = test.selectApplication (byteArrayToHexString(AID_ADF1));
        //- Select for termination ADF1
        response = test.sendApdu ("00A4044C 10"+byteArrayToHexString(AID_ADF1) );
        testresult &= response.checkSw("9000");
        //TC 25
        test.reset();
        //no terminal profile is sent and proactive handler shall not be available.
        response = test.envelopeMenuSelection("900102","9500");
        //TC 26
        response = test.envelopeMenuSelection("900101","");
        //TC 27
        response = test.envelopeTimerExpiration("A40101");
        //TC 28
        response = test.envelopeCallControlByNAA();
        //TC 29
        response = test.envelopeEventDownloadMTCall();
        //TC 30
        response = test.envelopeEventDownloadCallConnected();
        //TC 31
        response = test.envelopeEventDownloadCallDisconnected();
        //TC 32
        response = test.envelopeEventDownloadLocationStatus();
        //TC 33
        response = test.envelopeEventDownloadUserActivity();
        //TC 34
        response = test.envelopeEventDownloadIdleScreenAvailable();
        //TC 35
        response = test.envelopeEventDownloadCardReaderStatus();
        //TC 36
        response = test.envelopeEventDownloadLanguageSelection();
        //TC 37
        response = test.envelopeEventDownloadBrowserTermination();
        //TC 38
        response = test.status("00","0C","00");
        //TC 39
        response = test.unrecognizedEnvelope();
        //TC 40
        response = test.envelopeEventDownloadAccessTechnologyChange();
        //TC 41
        response = test.envelopeEventDownloadDisplayParametersChanged();
        //TC 49
        response = test.envelopeEventDownloadFramesInformationChanged();
        //TC 50
        response = test.envelopeEventDownloadHCIConnectivity();
        //TC 42
        //The Proactive Handler is not available before the
        //Terminal Profile
        //TC 43
   	response = test.envelopeEventDownloadNetworkSearchModeChange();
   	 //TC 44
   	response = test.envelopeEventDownloadBrowsingStatus();
        //TC 45 + 2
   	test.reset();
        //initialization with all the facilities supported (without SETUP_EVENT_LIST)
        response = test.terminalProfileSession("FFFFFFFF FEFFFFFF FFFFFFFF FFFFFFFF " +
                                               "FFFFFFFF FFFFFFFF FFFFFFFF FFFFFFFF " +
                                               "FFFF");
   	 	response = test.envelopeEventDownloadNetworkSearchModeChange();
	        testresult &= response.checkSw("9000");
   	 	//TC 46
   	 	response = test.envelopeEventDownloadBrowsingStatus();
   	        testresult &= response.checkSw("9000");
        //TC 47
        test.reset();
        // Install Applet
        //initialization with all the facilities supported (without SETUP_EVENT_LIST)
        response = test.terminalProfileSession("FFFFFFFF FEFFFFFF FFFFFFFF FFFFFFFF " +
                                               "FFFFFFFF FFFFFFFF FFFFFFFF FFFFFFFF " +
                                               "FFFF");
        test.installApplet(CAP_FILE_PATH, CLASS_AID_3, APPLET_AID_3,
                            "8008" + // TLV UICC Toolkit application specific parameters
                            "01" +   // V Priority Level
                            "00" +   // V Max. number of timers
                            "00" +   // V Maximum text length for a menu entry
                            "00" +   // V Maximum number of menu entries
                            "00" +   // V Maximum number of channels
                            "00" +   // LV Minimum Security Level field
                            "00" +   // LV TAR Value(s)
                            "00" );  // V Maximum number of services
        response = test.selectApplication(APPLET_AID_3);


        // get result from applets
   	    test.reset();
        // It is possible, based on implemenation of the card manufacturer, in
        // case that a CAT facility is not supported by the terminal profile,
        // the applet will not be triggered on an unsupported event.
   		response = test.selectApplication(APPLET_AID_1);
   		testresult &=  (response.checkData("10" +APPLET_AID_1 +
      									"2FCCCCCC CCCCCCCC CCCCCCCC CCCCCCCC"+
      									"CCCCCCCC CCCCCCCC CCCCCCCC CCCCCCCC"+
      									"CCCCCCCC CCCCCCCC CCCCCCCC CCCC") ||
                        response.checkData("10" +APPLET_AID_1 +
                                        "18CCCCCC CCCCCCCC CCCCCCCC CCCCCCCC" +
                                        "CCCCCCCC CCCCCCCC CC") );

   		response = test.selectApplication(APPLET_AID_2);
   		testresult &=  (response.checkData("10" +APPLET_AID_2 +
										"22CCCCCC CCCCCCCC CCCCCCCC CCCCCCCC"+
										"CCCCCCCC CCCCCCCC CCCCCCCC CCCCCCCC"+
										"CCCCCC") ||
                        response.checkData("10" +APPLET_AID_2 +
                                        "11CCCCCC CCCCCCCC CCCCCCCC CCCCCCCC " +
                                        "CCCC") );

   		response = test.selectApplication(APPLET_AID_3);
   		testresult &=  response.checkData("10" +APPLET_AID_3 + "01CC");

   		//  delete applet and package
		test.reset();
        test.terminalProfileSession(UiccCardManagementService.DEFAULT_TERMINAL_PROFILE);
		test.deleteApplet(APPLET_AID_1);
		test.deleteApplet(APPLET_AID_2);
		test.deleteApplet(APPLET_AID_3);
		test.deletePackage(CAP_FILE_PATH);

    	return testresult;
	}

	/**
	 * Converts a byte array to a hex string
	 * @param byteArray byte array[]
	 * @return the byte array as hex string: <br>
	 * E.g: byteArray{(byte)0x11,(byte)0x22,(byte)0x33} will be returned
	 * as String "112233"
	 */
	private String byteArrayToHexString(byte[] byteArray){
		StringBuffer stringBuffer = new StringBuffer();
		for(int i=0; i<byteArray.length ; i++){
			if ((byteArray[i] & 0xFF) <= 0xF){
				stringBuffer.append("0");
			}
			stringBuffer.append(Integer.toHexString(byteArray[i] & 0xff));
		}
		return stringBuffer.toString();
	}

    /**
     * Parse the service record TLV from a proactive declare service command.
     * @param declareServiceCommand proactive declare service command
     * @return service record TLV service record TLV
     */
   public String getServiceRecordTLV(String declareServiceCommand){
        String temp = declareServiceCommand.replaceAll(" ",""); //don't bother with formatted strings
        if (temp.length()>22){
            temp=temp.substring(22);//cut off BER TLV, len command details and  dev id
            int len = Integer.valueOf(temp.substring(2,4),16).intValue();
            if (len==temp.length()){
                return temp;
            }
            else{
                // suppress UICC/terminal interface TLV
                return temp.substring(0,(Integer.valueOf(temp.substring(2,4),16).intValue())*2+4);
            }
        }
        else return "";

    }





}
