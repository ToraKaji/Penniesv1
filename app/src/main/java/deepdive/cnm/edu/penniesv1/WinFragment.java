package deepdive.cnm.edu.penniesv1;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class WinFragment extends Fragment {

  private Button newGame;
  private long won;
  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Bundle args = getArguments();
    won = args.getLong("won");

  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_win, container, false);
    TextView coins = view.findViewById(R.id.coins);
    coins.setText(String.valueOf(won));
    newGame = view.findViewById(R.id.collect);
    newGame.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        getFragmentManager().beginTransaction().replace(R.id.fragment_container, new PennyPopper())
            .commit();
        getFragmentManager().popBackStack();
      }
    });
    return view;
  }

}
