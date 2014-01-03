package com.android.stcp.popup;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.android.stcp.R;

public class OrigemDestinoDialog extends DialogFragment {
	private View.OnClickListener positive;
	private View.OnClickListener negative;

	public void setButtons(final View.OnClickListener mPositive,
			final View.OnClickListener mNegative) {
		this.positive = mPositive;
		this.negative = mNegative;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		final Dialog mDialog = new Dialog(getActivity());

		mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

		mDialog.setContentView(R.layout.alert_destination_popup);
		mDialog.setCanceledOnTouchOutside(false);

		Button accept = (Button) mDialog.findViewById(R.id.button_origem);
		Button decline = (Button) mDialog.findViewById(R.id.button_destino);

		accept.setOnClickListener(positive);
		decline.setOnClickListener(negative);

		return mDialog;

	}
}
