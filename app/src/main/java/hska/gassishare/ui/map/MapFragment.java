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

    /**
     * Wird aufgerufen, wenn das Fragment erstellt wird.
     *
     * @param savedInstanceState Zustand des Fragments
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /* Lädt die Konfiguration für die OSM-Karte */
        /* @param ctx - Kontext der Aktivität */
        Context ctx = getActivity().getApplicationContext();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));
    }

    /**
     * Erstellt und gibt die Benutzeroberfläche des Fragments zurück.
     *
     * @param inflater           Der LayoutInflater, der verwendet wird, um die View zu erstellen.
     * @param container          Die ViewGroup, zu der die View hinzugefügt wird.
     * @param savedInstanceState Ein Bundle mit dem zuletzt gespeicherten Zustand des Fragments.
     * @return Die erstellte View des Fragments.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /* Erstellt die Benutzeroberfläche für das Fragment */
        /* @param inflater - Objekt zum Aufblasen der XML-Ressource */
        /* @param container - Ansichtsgruppe, in der das Fragment angezeigt wird */
        /* @param savedInstanceState - Zustand des Fragments */
        return inflater.inflate(R.layout.fragment_map, container, false);
    }

    /**
     * Wird aufgerufen, nachdem die View des Fragments erstellt wurde.
     *
     * @param view               Die erstellte View des Fragments.
     * @param savedInstanceState Ein Bundle mit dem zuletzt gespeicherten Zustand des Fragments.
     */
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        map = getView().findViewById(R.id.map);

        /* Überprüft und fordert Berechtigungen an, wenn erforderlich */
        /* @param permissions - Liste der anzufordernden Berechtigungen */
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
                        return true;
                    }
                    @Override
                    public boolean onItemLongPress(final int index, final OverlayItem item) {
                        return true;
                    }
                });

        // Zeigt das Infofenster des Markers an, wenn er angetippt wird
        mOverlay.setFocusItemsOnTap(true);

        // Fügt das Overlay der MapView hinzu
        map.getOverlays().add(mOverlay);
    }

    /**
     * Wird aufgerufen, wenn das Fragment wieder aktiv wird.
     */
    @Override
    public void onResume() {
        super.onResume();
        map.onResume();
    }

    /**
     * Wird aufgerufen, wenn das Fragment pausiert wird.
     */
    @Override
    public void onPause() {
        super.onPause();
        map.onPause();
    }

    /**
     * Wird aufgerufen, nachdem die Berechtigungen angefordert wurden.
     *
     * @param requestCode  Der Anforderungscode.
     * @param permissions  Liste der angeforderten Berechtigungen.
     * @param grantResults Ergebnis der Berechtigungsanforderung.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
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

    /**
     * Fordert Berechtigungen an, wenn sie noch nicht erteilt wurden.
     *
     * @param permissions Liste der zu überprüfenden Berechtigungen.
     */
    private void requestPermissionsIfNecessary(String[] permissions) {
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
