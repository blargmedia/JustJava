package ca.blargmedia.justjava;

/**
 * Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 * <p>
 * package com.example.android.justjava;
 */

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the + button is clicked.
     */
    public void increment(View view) {
        if (quantity == 100) {
            Toast.makeText(this, "max quantity reached", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity++;
        displayQuantity(quantity);
    }

    /**
     * This method is called when the - button is clicked.
     */
    public void decrement(View view) {
        if (quantity == 1) {
            Toast.makeText(this, "min quantity reached", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity--;
        displayQuantity(quantity);
    }

   /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        EditText nameText = (EditText) findViewById(R.id.name_text);
        String name = nameText.getText().toString();

        CheckBox whippedCreamCheckbox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean whippedCream = whippedCreamCheckbox.isChecked();

        CheckBox chocolateCheckbox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean chocolate = chocolateCheckbox.isChecked();

        int price = calculatePrice(whippedCream, chocolate);;
        String priceMessage = createOrderSummary(name, price, whippedCream, chocolate);
        displayMessage(priceMessage);

        composeEmail("Java order for " + name, priceMessage);
    }

    public void composeEmail(String subject, String body) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, body);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int n) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + n);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    /**
     * Calculates the price of the order based on the current quantity.
     *
     * @return the price
     */
    private int calculatePrice(boolean hasWhippedCream, boolean hasChocolate) {
        int price = 0;
        int coffeeOnly = 5;
        price += coffeeOnly;
        if (hasWhippedCream) {
            price += 1;
        }
        if (hasChocolate) {
            price += 2;
        }
        price *= quantity;
        return price;
    }

    /**
     * Creates an order summary
     *
     * @param price the price of the drink
     * @return  the order summary
     */
    private String createOrderSummary(String name, int price, boolean hasWhippedCream, boolean hasChocolate) {
        String summary = "Name: " + name;
        summary += "\nQuantity: " + quantity;
        if(hasWhippedCream) {
            summary += "\nAdd whipped cream";
        }
        if(hasChocolate) {
            summary += "\nAdd chocolate";
        }
        summary += "\nTotal: $" + calculatePrice(hasWhippedCream,hasChocolate) + "\nThanks!";

        return summary;
    }

}