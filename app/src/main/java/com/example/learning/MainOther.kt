//package com.example.learning
//import android.app.AlertDialog
//import android.content.Context
//import android.os.Bundle
//import android.os.Handler
//import android.os.Looper
//import android.view.LayoutInflater
//import android.widget.EditText
//import androidx.appcompat.app.AppCompatActivity
//
//class OtherMain : AppCompatActivity() {
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_other_main)
//
//        showCustomDialog(this)
//    }
//
//    private fun showCustomDialog(context: Context) {
//        // Використовуємо LayoutInflater для завантаження кастомного XML для діалогу
//        val dialogView = LayoutInflater.from(context).inflate(R.layout.othermain, null)
//
//        // Створюємо AlertDialog з кастомним виглядом
//        val dialogBuilder = AlertDialog.Builder(context)
//            .setView(dialogView)
//            .setCancelable(false) // Забороняємо закривати діалог поза межами
//            .setPositiveButton("Підтвердити") { dialog, _ ->
//                val editText = dialogView.findViewById<EditText>(R.id.editTextPassword)
//                val password = editText.text.toString()
//
//                // Дії після введення пароля
//                if (password == "1234") {
//                    dialog.dismiss()
//                } else {
//                    // Якщо пароль неправильний
//                }
//
//                dialog.dismiss()
//            }
//            .setNegativeButton("Відміна") { dialog, _ ->
//                dialog.cancel()
//            }
//
//        // Показуємо діалог
//        val alertDialog = dialogBuilder.create()
//        alertDialog.show()
//
//        // Створюємо таймер, який закриє діалог через 10 секунд
//        Handler(Looper.getMainLooper()).postDelayed({
//            if (alertDialog.isShowing) {
//                alertDialog.dismiss() // Закриваємо діалог автоматично
//            }
//        }, 10000) // 10000 мс = 10 секунд
//    }
//}
