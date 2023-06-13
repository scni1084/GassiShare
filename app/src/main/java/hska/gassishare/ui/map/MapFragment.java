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
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.ItemizedOverlayWithFocus;
import org.osmdroid.views.overlay.OverlayItem;

import java.util.ArrayList;

import hska.gassishare.R;
import hska.gassishare.databinding.FragmentMapBinding;

public class MapFragment extends Fragment {
    private final int REQUEST_PERMISSIONS_REQUEST_CODE = 1;
    private MapView map;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Context ctx = getActivity().getApplicationContext();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_map, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        map = getView().findViewById(R.id.map);
        //map.setTileSource(TileSourceFactory.MAPNIK);

        requestPermissionsIfNecessary(new String[]{
                // if you need to show the current location, uncomment the line below
                // Manifest.permission.ACCESS_FINE_LOCATION,
                // WRITE_EXTERNAL_STORAGE is required in order to show the map
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        });

        map.setBuiltInZoomControls(true);
        map.setMultiTouchControls(true);

        // Karlsruhe als Standort setzen
        IMapController mapController = map.getController();
        mapController.setZoom(16);
        GeoPoint startPoint = new GeoPoint(49.014, 8.404);
        mapController.setCenter(startPoint);

        //Standortmarker setzen
        // Create an ArrayList to hold your items
        ArrayList<OverlayItem> items = new ArrayList<OverlayItem>();
        Drawable doggoIcon1 = ContextCompat.getDrawable(getActivity(), R.drawable.doggo_marker1);
        Drawable doggoIcon2 = ContextCompat.getDrawable(getActivity(), R.drawable.doggo_marker2);
        Drawable doggoIcon3 = ContextCompat.getDrawable(getActivity(), R.drawable.doggo_marker3);

        // Add an item for each marker you want to display
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

        // Create a new ItemizedIconOverlay with your items
        ItemizedOverlayWithFocus<OverlayItem> mOverlay = new ItemizedOverlayWithFocus<OverlayItem>(getActivity(), items,
                new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>() {
                    @Override
                    public boolean onItemSingleTapUp(final int index, final OverlayItem item) {
                        // Do something when item is tapped (optional)
                        return true;
                    }
                    @Override
                    public boolean onItemLongPress(final int index, final OverlayItem item) {
                        // Do something when item is long-pressed (optional)
                        return true;
                    }
                });

        // Set to true if you want the item's info window to appear when it's tapped (optional)
        mOverlay.setFocusItemsOnTap(true);

        // Add the overlay to the MapView
        map.getOverlays().add(mOverlay);
    }

    @Override
    public void onResume() {
        super.onResume();
        map.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        map.onPause();
    }

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