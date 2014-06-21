package com.github.ikuo.android_error_dialog

import java.io.{StringWriter, PrintWriter}
import android.app.DialogFragment

class ErrorDialogFragment extends DialogFragment {
}

object ErrorDialogFragment {
  val stackTraceKey = "STACK_TRACE"
  val messageKey = "MESSAGE"
}
