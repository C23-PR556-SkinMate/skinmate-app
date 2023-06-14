package com.kdt.skinmate.data.ui.camera

import androidx.appcompat.app.AppCompatActivity

class ScanResultActivity : AppCompatActivity() {
//    private lateinit var ivScanResult: ImageView
//    private lateinit var rvSkinIssues: RecyclerView
//    private lateinit var rvRecommendedProducts: RecyclerView
//
//    private val skinIssues: List<String> = listOf("Jerawat", "Flek Hitam", "Kulit Kering")
//    private val recommendedProducts: List<String> = listOf("Produk A", "Produk B", "Produk C")
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_scan_result)
//
//        ivScanResult = findViewById(R.id.ivScanResult)
//        rvSkinIssues = findViewById(R.id.rvSkinIssues)
//        rvRecommendedProducts = findViewById(R.id.rvRecommendedProducts)
//
//        // Ambil hasil scan wajah dari intent atau sumber data lainnya
//        val scanResultImageResource = R.drawable.scan_result_image
//        ivScanResult.setImageResource(scanResultImageResource)
//
//        // Tampilkan daftar masalah kulit dalam RecyclerView
//        val skinIssuesAdapter = SkinIssuesAdapter(skinIssues)
//        rvSkinIssues.adapter = skinIssuesAdapter
//
//        // Tampilkan daftar produk rekomendasi dalam RecyclerView
//        val recommendedProductsAdapter = RecommendedProductsAdapter(recommendedProducts)
//        rvRecommendedProducts.adapter = recommendedProductsAdapter
//    }
//    private val REQUEST_IMAGE_CAPTURE = 1
//    private lateinit var ivScanResult: ImageView
//
//// ...
//
//    // Metode untuk memicu pengambilan gambar dari kamera
//    private fun captureImageFromCamera() {
//        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//        if (takePictureIntent.resolveActivity(packageManager) != null) {
//            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
//        }
//    }
//
//// ...
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
//            val imageBitmap = data?.extras?.get("data") as Bitmap?
//            ivScanResult.setImageBitmap(imageBitmap)
//        }
//    }
}
