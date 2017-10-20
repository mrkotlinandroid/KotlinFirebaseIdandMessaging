package android.com.firebaseidandmessaging

//all codes are taken   original documentaion and modified to kotlin langugage

//https://github.com/firebase/quickstart-android
import android.util.Log;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;


class MyFirebaseInstanceIDService : FirebaseInstanceIdService() {

    private val TAG: String = "MyFirebaseIIDService";

    override fun onTokenRefresh() {
        super.onTokenRefresh()
        var refreshedToken: String = FirebaseInstanceId.getInstance().getToken() as String;
        Log.d(TAG, "Refreshed token: " + refreshedToken);

    }

    private fun sendRegistrationToServer(token: String) {
        //save token to your backend for personalized notifications
    }
}