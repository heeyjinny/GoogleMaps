package com.heeyjinny.googlemaps

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.graphics.drawable.toBitmap
import androidx.viewbinding.BuildConfig

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.heeyjinny.googlemaps.databinding.ActivityMapsBinding

//2
//구글지도가 준비되면 OnMapReadyCallback 인터페이스의 onMapReady()메서드를 호출하면서
//파라미터로 준비된 GoogleMap을 전달해줌
class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    //2-1
    //매서드 안에 미리 mMap프로퍼티 생성
    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //1
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        // =SupportMapFragment를 가져와 map의 사용 준비가 완료되면 알려줌
        //SupportMapFragment의 findFragmentById()메서드로
        //id가 map인 SupportMapFragment를 찾은 것을 저장한 변수 생성
        //변수는 getMapAsync()를 호출해 안드로이드에 구글 지도를 그려달라는 요청을 함
        //getMapAsync()는 메인 스레드에서 호출해야 함
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    //2-3
    //미리 선언된 mMap변수에 GoogleMap을 저장해두면 액티비티 전체에서 맵 사용가능
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        //3
        //*****마커 표시 후 카메라 포지션 설정 뒤 마커 아이콘 변경
        //3-1
        //위치 옵션을 위해 위도 경도 고정좌표를 가지고 있는 변수 생성
        //서울시청의 위도와 경도
        val LATLNG = LatLng(37.566418, 126.977943)

        //3-2
        //위도와 경도 좌표값을 가지고 있는 변수를 이용해 마커추가
        //마커 옵션 정의를 위해 MarkerOptions()객체 필요
        //MarkerOptions()객체생성 후
        //position(): 마커 좌표 설정
        //title(): 마커 클릭 시 제목 설정
        //snippet(): 마커 클릭 시 정보 설정
        //icon(BitmapDescriptorFactory.fromResource(리소스위치))

        //비트맵 이미지를 이용해 마커 아이콘 변경
        //drawable 디렉터리에 PNG이미지 파일 추가
        //최상단 탭 File - New - Image Asset
        //벡터이미지는 안되며 .png 나 .jpg만 됨

        //마커 아이콘은 항상 BitmapDescriptor로 설정되며
        //BitmapDescriptorFactory 클래스의 메서드 중 하나를 사용하여 정의
        //메서드 중 비트맵 이미지의 리소스 ID를 사용하여 맞춤 마커를 만드는
        //fromResource(int resourceId) 메서드 사용
        val markerOptions = MarkerOptions()
            .position(LATLNG)
            .title("Seoul City Hall")
            .snippet("위치 좌표: ${(LATLNG.toString())}")
            .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_car))
        //3-3
        //GoogleMap 객체의 addMarker() 메서드에 MarkerOption 전달
        //지도에 마커 추가됨
        mMap.addMarker(markerOptions)

        //3-4
        //CameraPosition.Builder객체로 카메라 포지션 옵션 설정
        //카메라를 좌표 변수로 이동 후 줌을 거리레벨로 확대 하고
        //build()메서드 호출하여 객체 생성
        val cameraPosition = CameraPosition.Builder().target(LATLNG).zoom(15.0f).build()
        //3-5
        //카메라 포지션 옵션을 가지고 있는 변수를 새로운 카메라 포지션으로 변경
        //CameraUpdateFactory.newCameraPosition()메서드에 변수 전달하여
        //카메라 포지션에 지도에서 사용할 수 있는 카메라 정보 생성
        val cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition)
        //3-6
        //변경된 카메라 정보를 GoogleMap의 moveCamera()메서드 전달
        //카메라 포지션을 기준으로 지도의 위치 및 배율이 변경됨
        mMap.moveCamera(cameraUpdate)








    }
}//MapsActivity