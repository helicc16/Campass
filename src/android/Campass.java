package io.zapps.plugin.campass;


import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Campass extends CordovaPlugin {
    protected void pluginInitialize() {
    }

    public boolean execute(String action, JSONArray args, CallbackContext callbackContext)
            throws JSONException {
        if (action.equals("alert")) {
            alert(args.getString(0), args.getString(1), args.getString(2), callbackContext);
            return true;
        }
        else if ( action.equals("setAzimuth")){
            //Using Intent to start my own Camera Compass app
            Intent myIntent = new Intent();
            myIntent.setAction("io.zapps.camera13.CAMPASS");
            this.cordova.startActivityForResult((CordovaPlugin) this, myIntent, 2);
            return true;
        }
        return false;
    }

    //if camera compass operation is successful
    public void onActivityResult (int requestCode, int resultCode, Intent data, final CallbackContext callbackContext){
        //checking if the request  code is the same as what was requested

        if(requestCode == 2){

            String result = data.getStringExtra("BEARING");
            //this.azimuthSet = result;
            callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, result );
        }

    }
    private synchronized void alert(final String title,
                                    final String message,
                                    final String buttonLabel,
                                    final CallbackContext callbackContext) {
        new AlertDialog.Builder(cordova.getActivity())
                .setTitle(title)
                .setMessage(message)
                .setCancelable(false)
                .setNeutralButton(buttonLabel, new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int which) {
                        dialogInterface.dismiss();
                        callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, 0));
                    }
                })
                .create()
                .show();
    }
}
