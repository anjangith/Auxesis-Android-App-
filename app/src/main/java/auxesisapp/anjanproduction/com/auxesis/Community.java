package auxesisapp.anjanproduction.com.auxesis;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class Community extends Fragment {
    private Button button;


    public Community() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.from(getActivity()).inflate(R.layout.fragment_community,container,false);
        button=(Button)view.findViewById(R.id.communitybtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getActivity(),AuxCommunity.class);
                startActivity(i);
            }
        });

        return view;
    }

}
