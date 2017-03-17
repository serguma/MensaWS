package serguma.mensaws;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by g5 on 16/3/17.
 */

public class FragmentLista extends Fragment {

    private ListView listado;
    private ArrayList<Mensaje> datos = new ArrayList<Mensaje>();

    public FragmentLista() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();

        //cargo las prefs en datos, lo hago aquí para que la lista se vea siempre actualizada

        final SharedPreferences sp = getActivity().getSharedPreferences("mispfs", Context.MODE_PRIVATE);
        int numMensaje = sp.getInt("Num", 0);

        datos.clear();

        for(int i = 0; i<=numMensaje; i++){
            String mensaje = sp.getString("ID"+i, "");

            Log.d(getClass().getCanonicalName(), "MM: "+mensaje);
            Mensaje m = new Mensaje(mensaje);
            datos.add(i,m);
        }
        //Le doy la vuelta a los datos
        Collections.reverse(datos);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_lista, container, false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        listado = (ListView) getView().findViewById(R.id.listado);
        listado.setAdapter(new AdapterM(this));

    }

    class AdapterM extends ArrayAdapter<Mensaje> {

        Activity ctx;

        public AdapterM (Fragment ctx){
            super(ctx.getActivity(), R.layout.fila, datos);
            this.ctx = ctx.getActivity();
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View view = null;

            if(convertView == null){

                Activity activity = (Activity) ctx;
                LayoutInflater lista = activity.getLayoutInflater();
                view = lista.inflate(R.layout.fila, parent, false);

            }else{
                view = convertView;
            }

            //seteo valores

            TextView texto = (TextView) view.findViewById(R.id.enmensaje);
            texto.setText(datos.get(position).getTexto());

            return view;
        }

    }


}
