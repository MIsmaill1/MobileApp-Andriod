package com.HS.Topcity.Fragsment;

import static com.HS.Topcity.Activity.Guest.ui.GuestDashboard.guest;
import static com.HS.Topcity.Activity.ui.home.HomeFragment.notication_count;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.HS.Topcity.Activity.Dashboard;
import com.HS.Topcity.Activity.Notification;
import com.HS.Topcity.Activity.Signup;
import com.HS.Topcity.Activity.UserProfile;
import com.HS.Topcity.ApiModels.Masterplans.MasterplansResponse;
import com.HS.Topcity.ApiModels.NotificationList.NotificationListResponse;
import com.HS.Topcity.Common.ApiUtils;
import com.HS.Topcity.Common.PreferenceData;
import com.HS.Topcity.CustomClasses.TouchImageView;
import com.HS.Topcity.Interfaces.ApiInterface;
import com.HS.Topcity.R;
import com.bumptech.glide.load.resource.bitmap.BitmapResource;
import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.listener.OnPageErrorListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.shockwave.pdfium.PdfDocument;

import org.w3c.dom.Document;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MasterPlan#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MasterPlan extends Fragment implements OnPageChangeListener, OnPageErrorListener,OnLoadCompleteListener {

    ImageView back,user,notification,download;
    TouchImageView map;
  //  ZoomImageView map;
    LinearLayout signup;
    ApiInterface apiInterface;
    BitmapResource resource ;

    String link;
    TextView Noti_count;
    LinearLayout Noti_bg;
    WebView pdfView;
    Document   document;
    private static final String TAG = MasterPlan.class.getSimpleName();
    Uri  SAMPLE_FILE = null;
    PDFView pdfView1;
    Integer pageNumber = 0;
    Uri pdfFileName;
    // set maximum scroll amount (based on center of image)


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MasterPlan() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MasterPlan.
     */
    // TODO: Rename and change types and number of parameters
    public static MasterPlan newInstance(String param1, String param2) {
        MasterPlan fragment = new MasterPlan();
        Bundle args = new Bundle();
        args.putString( ARG_PARAM1, param1 );
        args.putString( ARG_PARAM2, param2 );
        fragment.setArguments( args );
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        if (getArguments() != null) {
            mParam1 = getArguments().getString( ARG_PARAM1 );
            mParam2 = getArguments().getString( ARG_PARAM2 );
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate( R.layout.fragment_master_plan, container, false );

        map = v.findViewById( R.id.masterPlan_map_image );
        back  = v.findViewById( R.id.back_tomasterpan );
        notification = v.findViewById( R.id.notification_masterplan );
        user = v.findViewById( R.id.user_icon_masterplan );
        download = v.findViewById( R.id.masterPlan_download );
        signup = v.findViewById( R.id.signup_btn_home );
        pdfView = v.findViewById( R.id.pdfView );
        Noti_count = v.findViewById( R.id.notification_Count );
        Noti_bg = v.findViewById( R.id.notification_Count_bg );
        Noti_count.setText( notication_count );



//        try {
//            convertPDFtoPNG();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
     //   pdf_view("https://docs.google.com/viewer?embedded=true&url=" + "https://firebasestorage.googleapis.com/v0/b/top-city-1-prod.appspot.com/o/ImageUseInProd%2Fupdated-plan.pdf");

        Noti_bg.setVisibility( View.GONE );
        if(Noti_count.getText().toString().equals( "0" )){
            Noti_bg.setVisibility( View.GONE );
        }
        else {
            Noti_bg.setVisibility( View.VISIBLE );
        }
// SAMPLE_FILE = Uri.parse("android.resource://" + v.getResources().getResourcePackageName( R.raw.updatedplan )  + "/" + R.raw.updatedplan);
        // guest
        guest();
        // pdf view web view

        // notification api
        Notification_list_api();

        apiInterface = ApiUtils.postSignUpService();
        ProgressDialog progress = new ProgressDialog(getActivity());
        progress.setMessage("Please wait while loading...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();
        String token = PreferenceData.getPrefUserToken(container.getContext());
        Call<MasterplansResponse> masterplanResponse = apiInterface.masterplanResponse(token);
        masterplanResponse.enqueue(new Callback<MasterplansResponse>() {
            @Override
            public void onResponse(Call<MasterplansResponse> call, Response<MasterplansResponse> response) {
                MasterplansResponse user = response.body();
                if(user != null) {

                    if(user.masterPlanModels != null){
                        link = user.masterPlanModels.getImage();

                        //                        username.setText(user.carInfoDetailModels.get(1).healthPercentage);
                //    Glide.with( getContext() ).load( user.masterPlanModels.getImage() ).into( map );
                     //   map.setOnTouchListener(new ImageMatrixTouchHandler(container.getContext()));


                    }
                    progress.dismiss();
                } else {
                    System.out.println("Failed");
                }
            }
            @Override
            public void onFailure(Call<MasterplansResponse> call, Throwable t) {
                Toast.makeText( container.getContext(), "api not work", Toast.LENGTH_SHORT ).show();
                System.out.println("Failed : " + t.getMessage());
                call.cancel();
            }

        });

        back.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(container.getContext(), Dashboard.class );
                a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity( a );
            }
        } );
        user.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(container.getContext(), UserProfile.class );
                a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity( a );
            }
        } );
        notification.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent( container.getContext(), Notification.class );
                a.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
                startActivity( a );
            }
        } );

//        download.setOnClickListener( new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                FileOutputStream fileOutputStream=null;
//                File file=getdisc();
//                if (!file.exists() && !file.mkdirs())
//                {
//                    Toast.makeText(getContext(),"sorry can not make dir",Toast.LENGTH_LONG).show();
//                    return;
//                }
//                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyymmsshhmmss");
//                String date=simpleDateFormat.format(new Date());
//                String name="img"+date+".jpeg";
//                String file_name=file.getAbsolutePath()+"/"+name; File new_file=new File(file_name);
//                try {
//                    fileOutputStream =new FileOutputStream(new_file);
//                    Bitmap bitmap= viewToBitmap(map,map.getWidth(),map.getHeight());
//                    bitmap.compress(Bitmap.CompressFormat.JPEG,100,fileOutputStream);
//                    Toast.makeText(getContext(),"sucses", Toast.LENGTH_LONG).show();
//                    fileOutputStream.flush();
//                    fileOutputStream.close();
//                }
//                catch
//                (FileNotFoundException e) {
//
//                } catch (IOException e) {
//
//                } refreshGallary(file);
//            } private void refreshGallary(File file)
//            { Intent i=new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
//                i.setData( Uri.fromFile(file));
//            }
//            private File getdisc(){
//                File file= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
//                return new File(file,"My Image");
//            }
//        } );


        download.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
                startActivity(browserIntent);

            }
        } );


        pdfView1 = (PDFView)v.findViewById(R.id.pdfView1);
       // displayFromAsset(SAMPLE_FILE);
        return v;
    }

//    public void convertPDFtoPNG() throws IOException {
//        Uri uri = Uri.parse(getResources().getResourceName( R.raw.masterplan ));
//        ParcelFileDescriptor fileDescriptor = ParcelFileDescriptor.open(new File( uri ), MODE_READ_ONLY);
//        PdfRenderer renderer = new PdfRenderer(fileDescriptor);
//        final int pageCount = renderer.getPageCount();
//        for (int i = 0; i < pageCount; i++) {
//            PdfRenderer.Page page = renderer.openPage(1);
//            Bitmap bitmap = Bitmap.createBitmap(page.getWidth(), page.getHeight(),Bitmap.Config.ARGB_8888);
//            Canvas canvas = new Canvas(bitmap);
//            canvas.drawColor( Color.WHITE);
//            canvas.drawBitmap(bitmap, 0, 0, null);
//            page.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY);
//            page.close();
//
//            map.setImageBitmap( bitmap );
//
//            String root = Environment.getExternalStorageDirectory().toString();
//            File file = new File(root +"masterPlain"+  ".png");
//
//            if (file.exists()) file.delete();
//            try {
//                FileOutputStream out = new FileOutputStream(file);
//                bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
//                Log.v("Saved Image - ", file.getAbsolutePath());
//                out.flush();
//                out.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
    private void displayFromAsset(Uri assetFileName) {
        pdfFileName = assetFileName;

        pdfView1.fromUri(assetFileName)
                .defaultPage(0)
                .onPageChange( this )
                .enableSwipe(true)
                .swipeHorizontal(false)
                .enableAnnotationRendering(true)
                .enableDoubletap( true )
                .onLoad(this)
                .scrollHandle(new DefaultScrollHandle(getContext()))
                .load();
    }


    @Override
    public void onPageChanged(int page, int pageCount) {
        pageNumber = page;
      //set(String.format("%s %s / %s", pdfFileName, page + 1, pageCount));
    }


    @Override
    public void loadComplete(int nbPages) {
        PdfDocument.Meta meta = pdfView1.getDocumentMeta();
        printBookmarksTree(pdfView1.getTableOfContents(), "-");

    }

    public void printBookmarksTree(List<PdfDocument.Bookmark> tree, String sep) {
        for (PdfDocument.Bookmark b : tree) {

     //       Log.e(TAG, String.format("%s %s, p %d", sep, b.getTitle(), b.getPageIdx()));

            if (b.hasChildren()) {
                printBookmarksTree(b.getChildren(), sep + "-");
            }
        }
    }

    @Override
    public void onPageError(int page, Throwable t) {
        Toast.makeText( getContext(), "not work", Toast.LENGTH_SHORT ).show();
    }

    private void pdf_view(String url){
         ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading Data...");
        progressDialog.setCancelable(false);
     //  pdfView.loadUrl("https://firebasestorage.googleapis.com/v0/b/top-city-1-prod.appspot.com/o/ImageUseInProd%2Fupdated-plan.pdf?alt=media&token=d33b4aef-9e72-4fbe-bb3e-e2137bee837f");
       pdfView.loadUrl( url );
  //      pdfView.loadUrl( "http://docs.google.com/gview?embedded=true&url=" + "https://firebasestorage.googleapis.com/v0/b/top-city-1-prod.appspot.com/o/ImageUseInProd%2Fupdated-plan.pdf?alt=media&token=d33b4aef-9e72-4fbe-bb3e-e2137bee837f");

        https://firebasestorage.googleapis.com/v0/b/top-city-1-prod.appspot.com/o/ImageUseInProd%2Fupdated-plan.pdf?alt=media&token=d33b4aef-9e72-4fbe-bb3e-e2137bee837f
       // pdfView.loadUrl("https://stackoverflow.com/questions/16207094/android-webview-not-loading-url");
        pdfView.getSettings().setSupportZoom(true);
        pdfView.getSettings().setBuiltInZoomControls( true );
       // pdfView.zoomBy(40f);

//        pdfView.getSettings().setLoadWithOverviewMode(true);
//        pdfView.getSettings().setUseWideViewPort(true);

      //  pdfView.setInitialScale(90);
        pdfView.getSettings().setLightTouchEnabled(true);
        pdfView.setBackgroundColor(getContext().getColor( R.color.white )  );
        pdfView.getSettings().setJavaScriptEnabled(true);
        pdfView.setPadding(0, 0, 0, 0);
        pdfView.setWebViewClient(new WebViewClient() );

        pdfView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        pdfView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                if (progress < 100) {
                    progressDialog.show();
                }
                if (progress == 100) {
                    progressDialog.dismiss();
                }
            }
        });
    }


    private void guest(){
        if(guest != null){
            Noti_bg.setVisibility( View.GONE );
            user.setVisibility( View.GONE );
            notification.setVisibility( View.GONE );
            signup.setVisibility( View.VISIBLE );
            signup.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent a  = new Intent(getContext(), Signup.class);
                    a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity( a );
                }
            } );
        }
    }
    private void saveimage() {
        Bitmap   bitmap = ((GlideBitmapDrawable)map.getDrawable().getCurrent()).getBitmap();
        String time  = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(System.currentTimeMillis());
        File path  = Environment.getExternalStorageDirectory();
        File dir = new File(path+"/Gallery");
        dir.mkdir();
        String imagename = time+".JPEG";
        File file = new File(dir,imagename);
        OutputStream out;
        try {
            out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,out);
            out.flush();
            out.close();
            Toast.makeText(getContext() ,"Image Save To Gallery",
                    Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            Toast.makeText(getContext(),"not work", Toast.LENGTH_SHORT).show();
        }
    }
    private void saveImage(Bitmap data) {
        File createFolder = new File( Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),"test");
        if(!createFolder.exists())
            createFolder.mkdir();
        File saveImage = new File(createFolder,"downloadimage.jpg");
        try {
            OutputStream outputStream = new FileOutputStream(saveImage);
            data.compress(Bitmap.CompressFormat.JPEG,100,outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static Bitmap viewToBitmap(View view, int widh, int hight)
    {
        Bitmap bitmap=Bitmap.createBitmap(widh,hight, Bitmap.Config.ARGB_8888);
        Canvas canvas=new Canvas(bitmap); view.draw(canvas);
        return bitmap;
    }
    private void Notification_list_api(){
        String token = PreferenceData.getPrefUserToken(getContext());

        apiInterface = ApiUtils.postSignUpService();

        Call<NotificationListResponse> notificationListResponseCall = apiInterface.notificationListResponse(token);
        notificationListResponseCall.enqueue(new Callback<NotificationListResponse>() {
            @Override
            public void onResponse(Call<NotificationListResponse> call, Response<NotificationListResponse> response) {
                NotificationListResponse user = response.body();


                if (user != null) {


                    if (user.notificationsList != null) {

                        notication_count = String.valueOf(user.NotificationBadgeCount  );
                        Noti_count.setText( notication_count );

                        if(Noti_count.getText().toString().equals( "0" )){
                            Noti_bg.setVisibility( View.GONE );
                        }
                        else {
                            if(guest != null){
                                guest();
                            }
                            else {
                                Noti_bg.setVisibility( View.VISIBLE );
                            }
                        }

                    }
                } else {
                    System.out.println( "Failed" );
                }

            }
            @Override
            public void onFailure(Call<NotificationListResponse> call, Throwable t) {
                System.out.println("Failed : " + t.getMessage());
                call.cancel();
            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();
       Notification_list_api();
    }
}