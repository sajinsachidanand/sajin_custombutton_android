package myapps.com.custombutton;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeLayoutContainer();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void initializeLayoutContainer() {
        CustomButton btnPrevious = new CustomButton(this);
        btnPrevious.setText("Previous");
        btnPrevious.setBackgroundColor(Color.parseColor("#3b5998"));
        btnPrevious.setFocusBackgroundColor(Color.parseColor("#5474b8"));
        btnPrevious.setTextSize(17);
        btnPrevious.setRadius(5);
        btnPrevious.setIconResource(android.R.drawable.ic_media_previous);
        btnPrevious.setIconPosition(CustomButton.POSITION_TOP);

        CustomButton btnPlay = new CustomButton(this);
        btnPlay.setText("Play");
        btnPlay.setBackgroundColor(Color.parseColor("#3b5998"));
        btnPlay.setFocusBackgroundColor(Color.parseColor("#5474b8"));
        btnPlay.setTextSize(17);
        btnPlay.setRadius(5);
        btnPlay.setIconResource(android.R.drawable.ic_media_play);
        btnPlay.setIconPosition(CustomButton.POSITION_TOP);

        CustomButton btnStop = new CustomButton(this);
        btnStop.setText("Stop");
        btnStop.setBackgroundColor(Color.parseColor("#3b5998"));
        btnStop.setFocusBackgroundColor(Color.parseColor("#5474b8"));
        btnStop.setTextSize(17);
        btnStop.setRadius(5);
        btnStop.setIconResource(android.R.drawable.ic_media_pause);
        btnStop.setIconPosition(CustomButton.POSITION_TOP);

        CustomButton btnFwd = new CustomButton(this);
        btnFwd.setText("Forward");
        btnFwd.setBackgroundColor(Color.parseColor("#3b5998"));
        btnFwd.setFocusBackgroundColor(Color.parseColor("#5474b8"));
        btnFwd.setTextSize(17);
        btnFwd.setRadius(5);
        btnFwd.setIconResource(android.R.drawable.ic_media_next);
        btnFwd.setIconPosition(CustomButton.POSITION_TOP);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, 0, 03, 10);
        LinearLayout container = (LinearLayout) findViewById(R.id.container);
        container.addView(btnPrevious, layoutParams);
        container.addView(btnPlay, layoutParams);
        container.addView(btnStop, layoutParams);
        container.addView(btnFwd, layoutParams);
    }
}
