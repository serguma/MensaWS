package serguma.mensaws;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by g5 on 16/3/17.
 */

public class FragmentForm extends Fragment{


    public FragmentForm() {


    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view  = inflater.inflate(R.layout.fragment_form, container, false);

        Button boton = (Button) view.findViewById(R.id.boton);
        boton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                final SharedPreferences sp = getActivity().getSharedPreferences("mispfs", Context.MODE_PRIVATE);
                int numMensaje = sp.getInt("Num", 0);

                TextView texto = (TextView) getActivity().findViewById(R.id.elmensaje);
                String elmensaje = texto.getText().toString();

                if(elmensaje.length() > 0){

                    try {
                        Intent sendIntent = new Intent();
                        sendIntent.setAction(Intent.ACTION_SEND);
                        sendIntent.putExtra(Intent.EXTRA_TEXT, elmensaje );
                        sendIntent.setType("text/plain");

                        //guardo el mensaje en la preferen

                        SharedPreferences.Editor ed = sp.edit();
                        ed.putString("ID"+numMensaje , elmensaje);
                        numMensaje = numMensaje + 1;
                        ed.putInt("Num", numMensaje);

                        ed.commit();

                        //llamo a whatsapp para enviar el mensaje
                        sendIntent.setPackage("com.whatsapp");
                        startActivity(sendIntent);


                    }catch (Exception e){
                        Toast.makeText(getActivity(), "Necesita WhatsApp para enviar el mensaje", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getActivity(), "El campo mensaje no puede estar vacío", Toast.LENGTH_SHORT).show();
                }
            }
        });





        return view;
    }

}
