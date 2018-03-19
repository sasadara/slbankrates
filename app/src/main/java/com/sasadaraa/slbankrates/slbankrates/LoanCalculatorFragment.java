package com.sasadaraa.slbankrates.slbankrates;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sasadaraa.slbankrates.slbankrates.databinding.LoanCalculatorBinding;

import java.text.DecimalFormat;


/**
 * Created by SasadaraA on 3/5/2018.
 */

public class LoanCalculatorFragment extends Fragment implements View.OnClickListener {

private LoanCalculatorBinding binding;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        binding = DataBindingUtil.inflate(
                inflater, R.layout.loan_calculator, container, false);
        View view = binding.getRoot();
        //here data must be an instance of the class MarsDataProvider
//        binding.setMarsdata(data);


        binding.amount.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {

                loan_Calculator();


            }
        });

        binding.loanPeriod.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                loan_Calculator();
            }
        });

        binding.interestRate.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                loan_Calculator();
            }
        });
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Loan Calculator");
    }

    public void loan_Calculator() {
        if(!binding.amount.getText().toString().equals("") && !binding.interestRate.getText().toString().equals("")&& !binding.loanPeriod.getText().toString().equals("")) {
            if (Double.parseDouble(binding.amount.getText().toString()) != 0 && Double.parseDouble(binding.interestRate.getText().toString()) != 0 && Double.parseDouble(binding.loanPeriod.getText().toString()) != 0) {
                double loan_amount, interest_rate, interest_rate_month, interest, unpaid_amount;
                double installment, principal;
                double total_interest = 0;
                int loan_period, loan_period_month;
                int payment_no = 0;
                int a;

                DecimalFormat twoDigits = new DecimalFormat(".00");

                loan_amount = Double.parseDouble(binding.amount.getText().toString());
                double initial_loan_amount = loan_amount;

                loan_period = Integer.parseInt(binding.loanPeriod.getText().toString());
                loan_period_month = loan_period * 12;
                interest_rate = Double.parseDouble(binding.interestRate.getText().toString());
                interest_rate_month = interest_rate / (12 * 100);

                installment = loan_amount * interest_rate_month / (1 - Math.pow(1 + interest_rate_month, -loan_period_month));



                for (a = 1; a <= loan_period_month; a++) {//loop for monthly calculation
                    payment_no++;//number indication for payment made
                    interest = loan_amount * loan_period_month / 100 / loan_period_month;//interest paid for the current month
                    principal = installment - interest;//monthly payment
                    unpaid_amount = loan_amount - principal;//balance amount to be paid
                    total_interest += interest;//increments the interest paid
                    loan_amount = unpaid_amount;//current loan balance

                }
                binding.installmentValue.setText("Installment Rate " + twoDigits.format(installment));
                binding.totalPayment.setText("Total Payment " + twoDigits.format(installment * loan_period_month));
                binding.ratePerLakhValue.setText("Rate Per One Lakh " + twoDigits.format(Math.pow(10,5) * installment/initial_loan_amount));
            }
        }
    }


    @Override
    public void onClick(View view) {
        switch ( (view.getId())){
/*            case R.id.calculateButton:
                loan_Calculator();
                break;*/
        }

    }
}