package com.github.ikuo.android_error_dialog

import java.io.{StringWriter, PrintWriter}
import android.app.{Activity, AlertDialog, Dialog, DialogFragment}
import android.content.DialogInterface
import android.os.Bundle
import android.widget.{Button, ImageButton, TextView}
import android.view.{LayoutInflater, ViewGroup, View}
import Tapper.Implicits._
import TypedResource._
import ErrorDialogFragment._

class ErrorDialogFragment extends DialogFragment with TypedViewHolder {
  private var stackTrace: String = "(unknown)"
  private var message: String = null

  private lazy val btnCollapse  = findView(TR.btnCollapse)
  private lazy val btnExpand    = findView(TR.btnExpand)
  private lazy val tvStackTrace = findView(TR.tvStackTrace)
  private lazy val tvMessage    = findView(TR.tvMessage)
  private var rootView: View = null

  override def findViewById(id: Int): View = this.rootView.findViewById(id)

  override def onCreateDialog(onActivityCreated: Bundle) = {
    val inflater = getActivity.getLayoutInflater
    this.rootView = inflater.inflate(TR.layout.error_dialog, null, false)

    val builder = new AlertDialog.Builder(getActivity)
    builder.setPositiveButton(getString(R.string.quit), onDialogClick { quit })
    builder.setNegativeButton(getString(R.string.show_crash_dialog),
      new DialogInterface.OnClickListener() {
        override def onClick(dialog: DialogInterface, whitch: Int): Unit = {
          throw new ExplicitErrorReport(stackTrace)
        }
      }
    )
    builder.setView(rootView)

    builder.create
  }

  override def onActivityCreated(state: Bundle): Unit = {
    super.onActivityCreated(state)
    readArgs(state)
    setEventListeners

    tvStackTrace.setText(stackTrace)
    if (message != null) {
      tvMessage.setText(message)
      tvMessage.setVisibility(View.VISIBLE)
    }
  }

  private def quit { dismiss; getActivity.finish }

  private def setEventListeners {
    btnCollapse.setOnClickListener(new View.OnClickListener {
      override def onClick(view: View): Unit = {
        btnCollapse.setVisibility(View.GONE)
        btnExpand.setVisibility(View.VISIBLE)
        tvStackTrace.setVisibility(View.GONE)
      }
    })

    btnExpand.setOnClickListener(new View.OnClickListener {
      override def onClick(view: View): Unit = {
        btnCollapse.setVisibility(View.VISIBLE)
        btnExpand.setVisibility(View.GONE)
        tvStackTrace.setVisibility(View.VISIBLE)
      }
    })
  }

  private def readArgs(bundle: Bundle): Unit = {
    this.stackTrace = readArg(bundle, stackTraceKey)
    this.message    = readArg(bundle, messageKey)
  }

  private def readArg(bundle: Bundle, key: String) = {
    var str = getArguments.getString(key)
    if (str == null && bundle != null) str = bundle.getString(stackTraceKey)
    str
  }

  private def onDialogClick[T](f: => T): DialogInterface.OnClickListener = {
    new DialogInterface.OnClickListener() {
      def onClick(dialog: DialogInterface, which: Int): Unit = { f }
    }
  }
}

object ErrorDialogFragment {
  val stackTraceKey = "stack_trace"
  val messageKey = "message"

  def apply(error: Throwable, message: String): ErrorDialogFragment =
    (new ErrorDialogFragment).tap {
      _.setArguments(
        (new Bundle).tap { b =>
          b.putString(stackTraceKey, stackTrace(error))
          Option(message).map { msg => b.putString(messageKey, msg) }
        }
      )
    }

  def apply(error: Throwable): ErrorDialogFragment = apply(error, null)

  private def stackTrace(error: Throwable): String = {
    val writer = new StringWriter()
    val out = new PrintWriter(writer)
    error.printStackTrace(out)
    writer.toString
  }

  class ExplicitErrorReport(string: String) extends RuntimeException(string)
}
