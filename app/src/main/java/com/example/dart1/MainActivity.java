package com.example.dart1;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Habilita el modo Edge-to-Edge
        Compiler EdgeToEdge = null;
        EdgeToEdge.enable();
        setContentView(R.layout.activity_main);

        // Ajuste de insets para evitar superposiciones con barras del sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Referencias a los elementos de la interfaz
        EditText ageInput = findViewById(R.id.ageInput);
        Button submitButton = findViewById(R.id.submitButton);
        TextView resultView = findViewById(R.id.resultView);
        Spinner breedSpinner = findViewById(R.id.breedSpinner);

        // Llenar el Spinner con las razas
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.dog_breeds, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        breedSpinner.setAdapter(adapter);

        // Acción al presionar el botón
        submitButton.setOnClickListener(v -> {
            String ageText = ageInput.getText().toString();
            String selectedBreed = breedSpinner.getSelectedItem().toString();

            if (!ageText.isEmpty()) {
                int age = Integer.parseInt(ageText);
                String dogAge = calculateDogAge(age, selectedBreed);
                resultView.setText(dogAge); // Muestra el resultado en el TextView
            } else {
                resultView.setText("Por favor, ingrese la edad.");
            }
        });
    }

    // Función para calcular la edad en años de un perrito basado en la raza
    private String calculateDogAge(int age, String breed) {
        int dogAge;

        // Ejemplo de cálculo basado en la raza
        switch (breed) {
            case "Pequeña":
                dogAge = age * 6;  // Razas pequeñas envejecen más lento
                break;
            case "Mediana":
                dogAge = age * 7;  // Razas medianas envejecen a un ritmo promedio
                break;
            case "Grande":
                dogAge = age * 8;  // Razas grandes envejecen más rápido
                break;
            default:
                dogAge = age * 7;  // Valor predeterminado
                break;
        }
        return "La edad del perrito (" + breed + ") es: " + dogAge + " años.";
    }
}
