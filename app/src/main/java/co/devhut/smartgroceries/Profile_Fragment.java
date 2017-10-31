package co.devhut.smartgroceries;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

//  fragment for the user profile
public class Profile_Fragment extends Fragment {

    public Profile_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);


        UserModel user = SharedPrefManager.getInstance(this.getContext()).getUser();


        TextView name = (TextView) view.findViewById(R.id.prof_nameDb);
        TextView surnameName = (TextView) view.findViewById(R.id.prof_surnameDb);
        TextView email = (TextView) view.findViewById(R.id.prof_emailDb);
        TextView dateJoined = (TextView) view.findViewById(R.id.prof_dateJoinnedDb);


        name.setText(user.getUsername());
        surnameName.setText(user.getUsername());
        email.setText(user.getEmail());
        dateJoined.setText("to be updated later");




        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_profile, container, false);
        return view;


    }

}
