package hska.gassishare.ui.map;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.ItemizedOverlayWithFocus;
import org.osmdroid.views.overlay.OverlayItem;

import java.util.ArrayList;

import hska.gassishare.R;

/**
 * Fragment für die Kartenansicht
 */
public class MapFragment extends Fragment {
    private final int REQUEST_PERMISSIONS_REQUEST_CODE = 1;
    private MapView map; // Ein View für die Kartenansicht

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Lädt die Konfiguration für die OSM-Karte
        // Parameter: ctx - Kontext der Aktivität
        Context ctx = getActivity().getApplicationContext();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Erstellt die Benutzeroberfläche für das Fragment
        // Parameter: inflater - Objekt zum Aufblasen der XML-Ressource
        //            container - Ansichtsgruppe, in der das Fragment angezeigt wird
        //            savedInstanceState - Zustand des Fragments
        // Rückgabetyp: View - aufgeblasene Ansicht für das Fragment
        return inflater.inflate(R.layout.fragment_map, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Wird aufgerufen, nachdem die Ansicht erstellt wurde
        // Parameter: view - erstellte Ansicht des Fragments
        //            savedInstanceState - Zustand des Fragments
        map = getView().findViewById(R.id.map);

        // Überprüft und fordert Berechtigungen an, wenn erforderlich
        // Parameter: permissions - Liste der anzufordernden Berechtigungen
        requestPermissionsIfNecessary(new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        });

        map.setBuiltInZoomControls(true);
        map.setMultiTouchControls(true);

        // Setzt den Standort auf Karlsruhe
        IMapController mapController = map.getController();
        mapController.setZoom(16);
        GeoPoint startPoint = new GeoPoint(49.014, 8.404);
        mapController.setCenter(startPoint);

        // Lädt Symbole für Hunde
        ArrayList<OverlayItem> items = new ArrayList<OverlayItem>();
        Drawable doggoIcon1 = ContextCompat.getDrawable(getActivity(), R.drawable.doggo_marker1);
        Drawable doggoIcon2 = ContextCompat.getDrawable(getActivity(), R.drawable.doggo_marker2);
        Drawable doggoIcon3 = ContextCompat.getDrawable(getActivity(), R.drawable.doggo_marker3);

        // Setzt Marker für Hunde
        GeoPoint pointDog1 = new GeoPoint(49.006889, 8.40299);
        GeoPoint pointDog2 = new GeoPoint(49.021435, 8.395378);
        GeoPoint pointDog3 = new GeoPoint(49.013173, 8.407080);
        OverlayItem dog1 = new OverlayItem("Bello", "Ein verspielter Bello", pointDog1);
        OverlayItem dog2 = new OverlayItem("Luna", "Eine fröhliche Luna", pointDog2);
        OverlayItem dog3 = new OverlayItem("Max", "Ein energiegeladener Max", pointDog3);

        dog1.setMarker(doggoIcon1);
        dog2.setMarker(doggoIcon2);
        dog3.setMarker(doggoIcon3);
        items.add(dog1);
        items.add(dog2);
        items.add(dog3);

        ItemizedOverlayWithFocus<OverlayItem> mOverlay = new ItemizedOverlayWithFocus<OverlayItem>(getActivity(), items,
                new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>() {
                    @Override
                    public boolean onItemSingleTapUp(final int index, final OverlayItem item) {
                        // Wird aufgerufen, wenn ein Marker einmal angetippt wird (optional)
                        return true;
                    }

                    @Override
                    public boolean onItemLongPress(final int index, final OverlayItem item) {
                        // Wird aufgerufen, wenn ein Marker lange gedrückt wird (optional)
                        return true;
                    }
                });

        // Zeigt das Infofenster des Markers an, wenn er angetippt wird (optional)
        mOverlay.setFocusItemsOnTap(true);

        // Fügt das Overlay der MapView hinzu
        map.getOverlays().add(mOverlay);
    }

    @Override
    public void onResume() {
        super.onResume();
        // Wird aufgerufen, wenn das Fragment wieder aktiv wird
        map.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        // Wird aufgerufen, wenn das Fragment pausiert wird
        map.onPause();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        // Wird aufgerufen, nachdem die Berechtigungen angefordert wurden
        // Parameter: requestCode - Anforderungscode
        //            permissions - Liste der angeforderten Berechtigungen
        //            grantResults - Ergebnis der Berechtigungsanforderung
        ArrayList<String> permissionsToRequest = new ArrayList<>();
        for (int i = 0; i < grantResults.length; i++) {
            permissionsToRequest.add(permissions[i]);
        }
        if (permissionsToRequest.size() > 0) {
            ActivityCompat.requestPermissions(
                    getActivity(),
                    permissionsToRequest.toArray(new String[0]),
                    REQUEST_PERMISSIONS_REQUEST_CODE);
        }
    }

    private void requestPermissionsIfNecessary(String[] permissions) {
        // Fordert Berechtigungen an, wenn sie noch nicht erteilt wurden
        // Parameter: permissions - Liste der zu überprüfenden Berechtigungen
        ArrayList<String> permissionsToRequest = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(getActivity(), permission)
                    != PackageManager.PERMISSION_GRANTED) {
                permissionsToRequest.add(permission);
            }
        }
        if (permissionsToRequest.size() > 0) {
            ActivityCompat.requestPermissions(
                    getActivity(),
                    permissionsToRequest.toArray(new String[0]),
                    REQUEST_PERMISSIONS_REQUEST_CODE);
        }
    }
}
