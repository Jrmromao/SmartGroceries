package co.devhut.smartgroceries;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

//  fragment for the user profile
public class Profile_Fragment extends Fragment {

    private Button editDetailBtn;

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
        dateJoined.setText(user.getDateJoined());


        // call the fragment to edit details
        editDetailBtn = (Button) view.findViewById(R.id.editDetails_btn);

        editDetailBtn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {

                Fragment f = new EditDetails_Fragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_profile, f, f.getTag()).commit();

            }
        });

        return view;


    }

}
