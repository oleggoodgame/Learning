package com.example.learning

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.BindingAdapter
import com.example.learning.databinding.ActivityMainBinding // імпорт Binding класу

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding // Оголошення змінної для Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val user = User("Олександр", 12)
        binding.user = user

        val vehicles = listOf(
            Car("Car"),
            Plane("Plane"),
            Ship("Ship")
        )

        // Перший Spinner для вибору типу транспортного засобу
        val vehicleNames = vehicles.map { it.name }
        //map — це функція в Kotlin, яка обробляє кожен елемент списку та перетворює його відповідно до виразу в { }. У цьому випадку:
        //Кожен об’єкт із vehicles перетворюється в його name. У результаті виходить список назв (List<String>), який складається з імен транспортних засобі
        val vehicleAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, vehicleNames)
        binding.spinner.adapter = vehicleAdapter

        // Слухач для оновлення другого Spinner в залежності від вибраного транспортного засобу
        //Це слухач подій для Spinner. Він дозволяє виконувати певний код, коли користувач вибирає елемент у Spinner або нічого не вибирає.
        // У цьому випадку використовується анонімний клас object для реалізації інтерфейсу AdapterView.OnItemSelectedListener.
        // Це зручно, тому що дозволяє реалізувати методи інтерфейсу прямо в місці виклику, без необхідності створювати окремий клас.
        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            //Метод onItemSelected викликається, коли вибрано елемент Spinner
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                //parent — це Spinner, який викликав подію.
                //position — індекс вибраного елемента.
                //selectedVehicle визначає обраний транспортний засіб за індексом.
                //brands містить список брендів, доступних для вибраного транспортного засобу.
                //Створюється новий адаптер brandAdapter, який застосовується до другого Spinner, щоб показати бренди.
                val selectedVehicle = vehicles[position]
                val brands = selectedVehicle.availableBrands()

                val brandAdapter = ArrayAdapter(this@MainActivity, android.R.layout.simple_spinner_item, brands)
                binding.spinner2.adapter = brandAdapter
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            //Метод onNothingSelected викликається, якщо нічого не вибрано у Spinner
            // (наприклад, при скиданні вибору або при першому завантаженні Spinner). Він необов'язковий, але може бути використаний для додаткових дій
            }
        }
        binding.button.setOnClickListener{
            val selectedVehiclePosition = binding.spinner.selectedItemPosition
            val selectedVehicle = vehicles[selectedVehiclePosition]
            val selectedBrand = binding.spinner2.selectedItem?.toString() ?: "No brand selected"

            selectedVehicle.drive()
            binding.textView.setText(selectedBrand)
            Log.d("SELECTED_BRAND", "Selected brand: $selectedBrand")
        }

        binding.button2.setOnClickListener {
            val dialogView = LayoutInflater.from(this).inflate(R.layout.alert_dialog, null)
            val alertDialog = AlertDialog.Builder(this)
                .setView(dialogView)
                .create()

            val progressBar = dialogView.findViewById<ProgressBar>(R.id.progressBar)
            val closeButton = dialogView.findViewById<Button>(R.id.closeButton)
            val messageTextView = dialogView.findViewById<TextView>(R.id.message)

            // Прогрес зворотного відліку
            var secondsLeft = 10
            progressBar.max = secondsLeft
            progressBar.progress = secondsLeft

            // Оновлення прогресу щосекунди
            val timerHandler = Handler(Looper.getMainLooper())
            val timerRunnable = object : Runnable {
                override fun run() {
                    if (secondsLeft > 0) {
                        secondsLeft--
                        progressBar.progress = secondsLeft
                        messageTextView.text = "Зачекайте $secondsLeft секунд..."
                        timerHandler.postDelayed(this, 1000)
                    } else {
                        alertDialog.dismiss()
                    }
                }
            }
            timerHandler.post(timerRunnable)

            // Закриття при натисканні на кнопку "Закрити"
            closeButton.setOnClickListener {
                alertDialog.dismiss()
                timerHandler.removeCallbacks(timerRunnable)
            }

            alertDialog.show()
        }
    }

}
// Не працює, хоча по документаці все ок, шукав в інеті але не робит, idk який kapt хоче а його у мене нема
//@BindingAdapter("customText")
//fun setCustomText(view: TextView, item: String) {
//    view.text = "My Custom String: $item"
//}

//import androidx.databinding.BaseObservable
//import androidx.databinding.Bindable
//import androidx.databinding.ObservableField
//
//// Використання BaseObservable для відслідковування змін
//class ObservableUser : BaseObservable() {
//    @get:Bindable
//    var name: String = ""
//        set(value) {ф
//            field = value
//            notifyPropertyChanged(BR.name)
//        }
//}
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel
//
//class UserViewModel : ViewModel() {
//    val userName: MutableLiveData<String> by lazy {
//        MutableLiveData<String>()
//    }
//}
//<TextView
//android:layout_width="wrap_content"
//android:layout_height="wrap_content"
//android:text="@{viewModel.userName}" />
//override fun onCreate(savedInstanceState: Bundle?) {
//    super.onCreate(savedInstanceState)
//
//    // Використання Data Binding з ViewModel
//    binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
//    viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
//
//    // Прив'язуємо ViewModel до binding
//    binding.viewModel = viewModel
//    binding.lifecycleOwner = this
//}