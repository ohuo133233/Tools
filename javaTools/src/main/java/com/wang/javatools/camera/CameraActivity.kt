package com.wang.javatools.camera

import android.Manifest
import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.wang.javatools.R
import com.wang.javatools.log.LogUtils
import java.io.File
import java.util.concurrent.Executors


class CameraActivity : AppCompatActivity(), View.OnClickListener {
    private val TAG = "CameraActivity"

    /**
     * View
     */
    private lateinit var previewView: PreviewView
    private lateinit var getCameraResources: ImageButton
    private lateinit var photo: TextView
    private lateinit var video: TextView

    /**
     * Data
     */
    // 拍照模式
    private val PHOTO = 1

    // 录像模式
    private val VIDEO = 2

    private var cameraMode: Int = PHOTO

    /**
     *Camera 相关
     */
    private lateinit var mImageCapture: ImageCapture
    private lateinit var mVideoCapture: VideoCapture


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)
        initView()

        initCamera()

    }

    private fun initCamera() {
        // 将相机的生命周期绑定到生命周期所有者。这消除了打开和关闭相机的任务，因为 CameraX 具有生命周期感知能力。
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)

        // 第二个参数：将返回 在主线程上运行的
        cameraProviderFuture.addListener({

            // 将相机的生命周期和activity的生命周期绑定，camerax 会自己释放，不用担心了
            var mCameraProvider = cameraProviderFuture.get()

            // 预览的capture，它里面支持角度换算
            var preview = Preview.Builder()
                .build()
            preview.setSurfaceProvider(previewView.surfaceProvider)

            // 创建图片的 capture
            mImageCapture = ImageCapture.Builder()
                .setFlashMode(ImageCapture.FLASH_MODE_AUTO)
                .build()

            // 录像用例配置
            // 设置音频源麦克风
            mVideoCapture = VideoCapture.Builder()
                .build()

            // 判断使用前置还是后置相机
            var cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA


            var mCamera = mCameraProvider.bindToLifecycle(
                this,
                cameraSelector,
                preview,
                mImageCapture
            )

        }, ContextCompat.getMainExecutor(this))


    }


    private fun initView() {
        previewView = findViewById(R.id.preview_view)
        getCameraResources = findViewById(R.id.get_camera_resources)
        photo = findViewById(R.id.photo)
        video = findViewById(R.id.video)

        getCameraResources.setOnClickListener(this)
        photo.setOnClickListener(this)
        video.setOnClickListener(this)
    }


    override fun onClick(v: View?) {
        if (v == null) {
            return
        }

        when (v.id) {
            R.id.get_camera_resources -> getCameraResources()
            R.id.photo -> cameraMode = PHOTO
            R.id.video -> cameraMode = VIDEO
        }
    }


    @SuppressLint("RestrictedApi")
    private fun getCameraResources() {
        if (cameraMode == PHOTO) {
            val img_path = File(filesDir.path + "imageName")
            // 创建包文件的数据，比如创建文件
            val outputFileOptions: ImageCapture.OutputFileOptions = ImageCapture.OutputFileOptions.Builder(img_path).build()
            mImageCapture.takePicture(outputFileOptions, ContextCompat.getMainExecutor(this),
                object : ImageCapture.OnImageSavedCallback {
                    override fun onImageSaved(@NonNull outputFileResults: ImageCapture.OutputFileResults) {
                        val savedUri: Uri? = outputFileResults.savedUri
                        Log.e(TAG, savedUri.toString())
                    }

                    override fun onError(@NonNull exception: ImageCaptureException) {
                        Log.e(TAG, exception.message.toString())
                    }
                })

        } else {
            val contentValues = ContentValues()
            contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, "recorded_video" + "_")
            contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "video/mp4")
            val outputFileOptions: VideoCapture.OutputFileOptions =
                VideoCapture.OutputFileOptions.Builder(
                    contentResolver,
                    MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                    contentValues)
                    .build()

            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.RECORD_AUDIO
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                Log.e(TAG,"没有权限")
                return
            }

            mVideoCapture.startRecording(
                outputFileOptions,
                Executors.newSingleThreadExecutor(),
                object : VideoCapture.OnVideoSavedCallback {
                    override fun onVideoSaved(p0: VideoCapture.OutputFileResults) {
                        Log.e(TAG,"onVideoSaved")
                    }

                    override fun onError(p0: Int, p1: String, p2: Throwable?) {
                        Log.e(TAG,"onError")
                    }

                })


        }

    }


}