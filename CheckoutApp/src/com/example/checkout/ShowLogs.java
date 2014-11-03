package com.example.checkout;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.print.PrintManager;
import android.view.View;

public class ShowLogs extends Activity {
	
	List<LogItem> logs;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	
	    SqlLiteYouMeanIt db = new SqlLiteYouMeanIt(this);
	    
	    logs = db.getAllLogs();
	}

	public void deleteLogs(View v){
		AlertDialog diaBox = AskOption();
		diaBox.show();
	}
	
	public void printLogs(View v){
		PrintManager printManager = (PrintManager) this
	            .getSystemService(Context.PRINT_SERVICE);

	    String jobName = this.getString(R.string.app_name) + 
                        " Document";

	    printManager.print(jobName, new PrintLogsAdapter(this),
	             null);
	}
	
	 private AlertDialog AskOption()
	 {
	    AlertDialog myQuittingDialogBox = new AlertDialog.Builder(this) 
	        //set message, title, and icon
	        .setTitle("Delete") 
	        .setMessage("Do you really want to delete all logs?")

	        .setPositiveButton("Delete", new DialogInterface.OnClickListener() {

	            public void onClick(DialogInterface dialog, int whichButton) { 
	                //your deleting code
	            	SqlLiteYouMeanIt db = new SqlLiteYouMeanIt(getBaseContext());
	        		db.deleteAllLogs();
	                dialog.dismiss();
	            }   

	        })



	        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
	            public void onClick(DialogInterface dialog, int which) {

	                dialog.dismiss();
	            }
	        })
	        .create();
	        return myQuittingDialogBox;

	    }
}
