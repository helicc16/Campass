package io.zapps.plugin.campass;


import android.content.Intent;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Campass extends CordovaPlugin {
    private CallbackContext myCallbackContext;
    
    //Constructor
    public Campass (){
   
    }

    public void initialize(CordovaInterface cordova, CordovaWebView webView){
        super.initialize(cordova, webView);
    }

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext)
            throws JSONException {

        if ( action.equals("setAzimuth")){



            //Using Intent to start my own Camera Compass app
            Intent myIntent = new Intent();
            myIntent.setAction("io.zapps.camera14.CAMPASS");
            this.cordova.setActivityResultCallback(this);
            this.cordova.getActivity().startActivityForResult( myIntent, 2);
            this.myCallbackContext = callbackContext;
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
            String result = data.getStringExtra("BEARING");
            this.myCallbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, result ));
            
        }       

    }

}
