package co.edu.udea.moviemapps.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import java.util.ArrayList;
import co.edu.udea.moviemapps.R;
import co.edu.udea.moviemapps.adapters.FuncionAdapter;
import co.edu.udea.moviemapps.listener.OnFragmentInteractionListener;
import co.edu.udea.moviemapps.model.Function;
import co.edu.udea.moviemapps.listener.OnItemFunctionListener;
import co.edu.udea.moviemapps.persistence.FuncionDataManager;

/**
 * Created by CamiGomez35 on 23/03/2016.
 */
public class Funciones extends Fragment implements View.OnClickListener, OnItemFunctionListener{
    public static final int ID =30;
    public static final String TITLE = "TITULO";
    RecyclerView rv;
    private RecyclerView.LayoutManager mLayoutManager;
    private OnFragmentInteractionListener mListener;

    public static Funciones newInstance(){
        return new Funciones();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.funcion_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rv = (RecyclerView) view.findViewById(R.id.funcion_list);

        mLayoutManager = new LinearLayoutManager(getContext());

        rv.setLayoutManager(mLayoutManager);
        ArrayList<Function> fun = FuncionDataManager.getInstance().getAllFunciones();

        FuncionAdapter adapter = new FuncionAdapter(fun, this);
        rv.setAdapter(adapter);
        rv.setOnClickListener(this);
        mListener = (OnFragmentInteractionListener) getActivity();
    }

    @Override
    public void onItemClick(Function funcion, View view, int position) {

        System.out.print("Hola Mundo");

        Bundle datos = new Bundle();
        datos.putString("TITULO", TITLE);
        datos.putString("LUGAR", funcion.getLugar());
        datos.putString("HORA", funcion.getHora());
        datos.putString("SALA", funcion.getSala());
        mListener.setFragment(InfoCompra.ID, datos, true);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.funcion_list:
                Toast.makeText(getContext(), "Funcion",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}