package com.example.geoquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private Button mPrevButton;
    private TextView mQuestionTextView;
    private int mCurrentIndex = 0;

    private Question[] mQuestionBank = new Question[]{
            new Question(R.string.question_australia, true),
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mQuestionTextView = findViewById(R.id.question_text_view);
        int textId = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(textId);
        mQuestionTextView.setText(mCurrentIndex+" " + mQuestionTextView.getText());

        mTrueButton = findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(true);
            }
        });

        mFalseButton = findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(false);
            }
        });

        mNextButton = findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
            }
        });

        mPrevButton = findViewById(R.id.prev_button);
        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int len = mQuestionBank.length;
                mCurrentIndex = Math.abs(len+((mCurrentIndex-1)%len))%len;
                updateQuestion();
            }
        });

    }

    private void updateQuestion() {
        int questionId = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(questionId);
        mQuestionTextView.setText(mCurrentIndex+" " + mQuestionTextView.getText());
    }

    private void checkAnswer(boolean userPressedButton) {

        boolean isAnswerTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();

        int answerId;

        if (userPressedButton == isAnswerTrue)
            answerId = R.string.correct_toast;
        else
            answerId = R.string.incorrect_toast;

        Toast.makeText(MainActivity.this, answerId, Toast.LENGTH_SHORT).show();
    }

}
