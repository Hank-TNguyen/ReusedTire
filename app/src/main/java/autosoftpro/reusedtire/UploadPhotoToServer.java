package autosoftpro.reusedtire;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

import it.sauronsoftware.ftp4j.FTPAbortedException;
import it.sauronsoftware.ftp4j.FTPClient;
import it.sauronsoftware.ftp4j.FTPDataTransferException;
import it.sauronsoftware.ftp4j.FTPDataTransferListener;
import it.sauronsoftware.ftp4j.FTPException;
import it.sauronsoftware.ftp4j.FTPIllegalReplyException;

/**
 * Created by Hung on 6/26/2016.
 */
public class UploadPhotoToServer extends AsyncTask<String, Void, Void> {

    FTPClient mFTPClient;
    private Context mContext;

    public UploadPhotoToServer(Context c){
        mContext = c;
    }


    public static final String IMAGE_FOLDER = "/reusedtire/androidApp/Images";
    /*********  work only for Dedicated IP ***********/
    static final String FTP_HOST= "173.201.146.128";

    /*********  FTP USERNAME ***********/
    static final String FTP_USER = "hanknguyen";

    /*********  FTP PASSWORD ***********/
    static final String FTP_PASS  ="the!H123";
    @Override
    protected Void doInBackground(String... params) {

        for (String imagePath : params) {
            File imagePathFile = new File(imagePath);
            try {
                mFTPClient = new FTPClient();
                mFTPClient.connect(FTP_HOST,21);
                mFTPClient.login(FTP_USER, FTP_PASS);
                mFTPClient.setType(FTPClient.TYPE_BINARY);
                mFTPClient.changeDirectory(IMAGE_FOLDER);

                Log.i("Upload image at ", imagePath);
                mFTPClient.upload(imagePathFile, new MyTransferListener());
                mFTPClient.disconnect(true);

            } catch (IOException e) {
                e.printStackTrace();
            } catch (FTPIllegalReplyException e) {
                e.printStackTrace();
            } catch (FTPException e) {
                e.printStackTrace();
            } catch (FTPAbortedException e) {
                e.printStackTrace();
            } catch (FTPDataTransferException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /*******  Used to file upload and show progress  **********/

    public class MyTransferListener implements FTPDataTransferListener {

        public void started() {

            // Transfer started
//            Toast.makeText(mContext, " Upload Started ...", Toast.LENGTH_SHORT).show();
            System.out.println(" Upload Started ...");
        }

        public void transferred(int length) {

            // Yet other length bytes has been transferred since the last time this
            // method was called
//            Toast.makeText(mContext, " transferred ..." + length, Toast.LENGTH_SHORT).show();
            System.out.println(" transferred ..." + length);
        }

        public void completed() {

            // Transfer completed

//            Toast.makeText(mContext, " completed ...", Toast.LENGTH_SHORT).show();
            System.out.println(" completed ..." );
        }

        public void aborted() {
            // Transfer aborted
//            Toast.makeText(mContext," transfer aborted please try again...", Toast.LENGTH_SHORT).show();
            System.out.println(" aborted ..." );
        }

        public void failed() {
            // Transfer failed
            System.out.println(" failed ..." );
        }

    }
}
