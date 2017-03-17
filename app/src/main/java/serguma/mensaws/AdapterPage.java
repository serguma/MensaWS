package serguma.mensaws;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by g5 on 16/3/17.
 */

public class AdapterPage extends FragmentStatePagerAdapter {

    public AdapterPage(FragmentManager fm) {
            super(fm);
        }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;

        switch (position){
            case 0: fragment = new FragmentForm();
                break;
            case 1: fragment = new FragmentLista();
                break;
        }

        return fragment;
    }

    @Override
    public int getCount() {
            return 2;
        }

    @Override
    public CharSequence getPageTitle(int position) {
        String response = "";

        switch (position){
             case 0: response = "Enviar";
                 break;
             case 1: response = "Historial";
                 break;
         }

        return response;


    }


}


