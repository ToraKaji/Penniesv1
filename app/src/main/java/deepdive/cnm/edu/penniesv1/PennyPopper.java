package deepdive.cnm.edu.penniesv1;

import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract.Intents.Insert;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import de.hdodenhof.circleimageview.CircleImageView;
import deepdive.cnm.edu.penniesv1.model.db.PenniesDB;
import deepdive.cnm.edu.penniesv1.model.entities.Play;
import deepdive.cnm.edu.penniesv1.model.entities.Scratcher;
import deepdive.cnm.edu.penniesv1.model.entities.User;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class PennyPopper extends Fragment {

  //Create an int array that holds id values of text views and buttons
  private static final int idButtonArray[] = {R.id.button0, R.id.button1, R.id.button2,
      R.id.button3,
      R.id.button4, R.id.button5, R.id.button6, R.id.button7, R.id.button8};
  private static final int idTextArray[] = {R.id.button0_val, R.id.button1_val, R.id.button2_val,
      R.id.button3_val, R.id.button4_val, R.id.button5_val, R.id.button6_val, R.id.button7_val,
      R.id.button8_val};

  //Create a scratch object
  Scratcher scratch = new Scratcher();
  //Creates an array of all known values
  int[] values = {-1, -2, -3, -4, -5, -6, -7, -8, -9};
  int buttonsClicked = 0;
  //
  Play play = new Play();
  User user = new User();
  PenniesDB db;
  //Sets the default status to loss
  private boolean win = false;
  private int height = 3;
  private int width = 3;
  private int valueWon;

  //Create an array of button and text view objects
  private CircleImageView[] button = new CircleImageView[idButtonArray.length];
  private TextView[] textVal = new TextView[idTextArray.length];

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_penny_popper, container, false);
    db = PenniesDB.getInstance(getActivity());
    for (int i = 0; i < idButtonArray.length; i++) {
      button[i] = createButton(view, play, i, idButtonArray[i], idTextArray[i]);
    }

    return view;
  }

  private CircleImageView createButton(final View view, Play play, final int num, int id,
      final int textId) {

    //Creates a RNG for the values of 0-50 in increments of 10.
    Random rng = new Random();
    final List<Integer> possible = new ArrayList<>();
    for (int i = 10; i <= 61; i += 10) {
      possible.add(i);
    }

    //Creates fields to set listeners and textview objects
    final int value = possible.get(rng.nextInt(possible.size() - 1));
    final TextView textX = view.findViewById(textId);
    final CircleImageView buttonX = view.findViewById(id);
    //Sets a entity object for each button
    final Scratcher scratch = new Scratcher();
    scratch.setId(num);
    scratch.setDate(new Date());
    scratch.setValue(value);
    //new InsertTask().execute(scratch);
    buttonX.setTag(scratch);

    //Sets onClickListeners for each button created.
    buttonX.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        //Notifies click
        scratch.setButtonClicked(buttonsClicked);
        ++buttonsClicked;
        //Sets up the entity class for DB.
        //Physical effects on the object.
        buttonX.setVisibility(View.INVISIBLE);
        textX.setText(String.valueOf(value));
        textX.setVisibility(View.VISIBLE);
        values[num] = value;

        checkWin();

      }
    });
    return buttonX;
  }

  private void saveGame(Play play, CircleImageView[] buttons) {
    new InsertTask(play).execute(buttons);
  }

  private void checkWin() {
    user.setCoins(user.getCoins()-2);
    //Checks for horizontal wins ( x==x+1&&x==x+2 == true linear horizontal win.)
    for (int x = 0; x < 7; x += width) {
      if (values[x] == values[x + 1] && values[x] == values[x + 2] && values[x] != 0) {
        valueWon = values[x];
        win = true;
        break;
      }
    }

    //Check for vertical wins ( x==x+3&&x==x+6 == true linear vertical win.)
    for (int x = 0; x < height; x += 1) {
      if (values[x] == values[x + 3] && values[x] == values[x + 6] && values[x] != 0) {
        valueWon = values[x];
        win = true;
        break;
      }
    }

    if (values[0] == values[4] && values[0] == values[8] && values[0] != 0) {
      valueWon = values[0];
      win = true;
    }

    if (values[2] == values[4] && values[2] == values[6] && values[2] != 0) {
      valueWon = values[2];
      win = true;
    }

    if (win) {
      user.setCoins(user.getCoins()+valueWon);
      play.setOutcome(true);
      play.setWon(valueWon);
      saveGame(play, button);
      WinFragment winFragment = new WinFragment();
      Bundle bundle = new Bundle();
      bundle.putLong("won",valueWon);
      winFragment.setArguments(bundle);
      getFragmentManager().beginTransaction().addToBackStack("win")
          .replace(R.id.game0, winFragment).commit();

    } else if (buttonsClicked == 9) {
      play.setOutcome(false);
      saveGame(play, button);

      getFragmentManager().beginTransaction().addToBackStack("loss")
          .replace(R.id.game0, new LossFragment()).commit();
    }


  }

  private class InsertTask extends AsyncTask<CircleImageView, Void, List<Long>> {

    private Play play;

    public InsertTask(Play play) {
      this.play = play;
    }

    @Override
    protected List<Long> doInBackground(CircleImageView... circles) {

      db.getUserDao().update(user);
      play.setUserId(user.getUserId());
      long playId = db.getPlayDao().insert(play);
      List<Scratcher> scratchers = new LinkedList<>();
      for (CircleImageView view : circles) {
        Scratcher scratcher = (Scratcher) view.getTag();
        scratcher.setPlayId(playId);
        scratchers.add(scratcher);
      }
      return db.getScratcherDao().insert(scratchers);
    }
  }

  private class InsertCoins extends AsyncTask<User, Void, User>{

    @Override
    protected User doInBackground(User... users) {
      db.getUserDao().update(users[0]);
      return users[0];
    }

    @Override
    protected void onPostExecute(User user) {
      super.onPostExecute(user);
      //TODO Update UI
    }
  }


}
