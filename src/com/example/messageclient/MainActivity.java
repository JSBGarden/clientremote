package com.example.messageclient;


import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

	private Socket client;
	private PrintWriter printwriter;
	private EditText ip,port,message;
	private Button send,up,left,right,down;
	private String sendMessage;
	private int sendPort=8081; 
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		ip= (EditText) findViewById(R.id.txtIP);
		port= (EditText) findViewById(R.id.txtPort);
		message= (EditText) findViewById(R.id.txtMessage);
		send = (Button) findViewById(R.id.btnSend);
		up= (Button) findViewById(R.id.btnUp);
		down= (Button) findViewById(R.id.btnDown);
		left= (Button) findViewById(R.id.btnLeft);
		right= (Button) findViewById(R.id.btnRight);
		send.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				sendMessage=message.getText().toString();
				message.setText("");
				//sendPort=Integer.parseInt(port.getText().toString());
				send(sendMessage);
				
				
			}
		});
		up.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				send("^");
				
			}
		});
		down.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				send("v");
				
			}
		});
		left.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				send("<");
				
			}
		});
		right.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				send(">");
				
			}
		});
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	public void send( final String a){
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try{
					client=new Socket(ip.getText().toString(),sendPort);
					printwriter=new PrintWriter(client.getOutputStream());
					printwriter.write(a);
					printwriter.flush();
					printwriter.close();
					client.close();
					
				}
				catch (UnknownHostException e){
					e.printStackTrace();
				}
				catch (IOException e){
					e.printStackTrace();
				}
				
			}
		}).start();
	}
}
