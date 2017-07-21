package ca.blargmedia.justjava;

/**
 * Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 * <p>
 * package com.example.android.justjava;
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

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
        quantity++;
        displayQuantity(quantity);
    }

    /**
     * This method is called when the - button is clicked.
     */
    public void decrement(View view) {
        quantity--;
        displayQuantity(quantity);
    }

   /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox whippedCreamCheckbox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        CheckBox chocolateCheckbox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        int price = calculatePrice();;
        String priceMessage = createOrderSummary(price, whippedCreamCheckbox.isChecked(), chocolateCheckbox.isChecked());
        displayMessage(priceMessage);
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
    private int calculatePrice() {
        int price = quantity * 5;
        return price;
    }

    /**
     * Creates an order summary
     *
     * @param price the price of the drink
     * @return  the order summary
     */
    private String createOrderSummary(int price, boolean hasWhippedCream, boolean hasChocolate) {
        String name = "foo";
        int total = quantity * price;
        String summary = "Name: " + name + "\nQuantity: " + quantity;
        if(hasWhippedCream) {
            summary += "\nAdd whipped cream";
        }
        if(hasChocolate) {
            summary += "\nAdd chocolate";
        }
        summary += "\nTotal: $" + calculatePrice() + "\nThanks!";

        return summary;
    }

}