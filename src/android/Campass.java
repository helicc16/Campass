package io.zapps.plugin.campass;

import android.content.Intent;
import android.app.Activity;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Campass extends CordovaPlugin {
    private CallbackContext callbackContext;
    //Constructor
    public Campass (){}

    public void initialize(CordovaInterface cordova, CordovaWebView webView){
        super.initialize(cordova, webView);
    }

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext)
            throws JSONException {

        if ( action.equals("setAzimuth")){



            /*
            cordova.getActivity().runOnUiThread( new Runnable(){
                public void run(){
                    //Using Intent to start my own Camera Compass app
                    Intent myIntent = new Intent();
                    myIntent.setAction("io.zapps.camera13.CAMPASS");
                    
                    cordova.getActivity().startActivityForResult(myIntent, 2);
                    
                }
            });
            */


            //Using Intent to start my own Camera Compass app
            Intent myIntent = new Intent();
            myIntent.setAction("io.zapps.camera13.CAMPASS");
            this.cordova.setActivityResultCallback(this);
            this.cordova.getActivity().startActivityForResult( myIntent, 2);

            //callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, "hi" ));
            return true;

        }
        else if (action.equals("echo")){
            String text = "Hello World";
            echoAString (text, callbackContext);
            return true;
        }
        
        else {
            return false;
        }
        
    }

    //if camera compass operation is successful
    public void onActivityResult (int requestCode, int resultCode, Intent data){
        //checking if the request  code is the same as what was requested

        if(requestCode == 2){
            String result = "degrees"; //data.getStringExtra("BEARING");
            callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, result ));
        }

           // String result = "degrees"; //data.getStringExtra("BEARING");
             //callbackContext.success(result);

    }



    
    public void echoAString (String message, CallbackContext callbackContext) {
        if (message != null && message.length() > 0) { 
            callbackContext.success(message);
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }
   
}
