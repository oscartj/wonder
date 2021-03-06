import com.webobjects.appserver.WOActionResults;
import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WOContext;
import com.webobjects.foundation.NSData;

import er.ajax.AjaxProgress;
import er.ajax.AjaxUploadProgress;

public class FileUploadExample extends WOComponent {
	public NSData _data;
	public AjaxUploadProgress _uploadProgress;
	public AjaxProgress _progress;

	public FileUploadExample(WOContext context) {
		super(context);
		_progress = new AjaxProgress(100);
		Thread progressThread = new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < _progress.maximum(); i++) {
					_progress.setValue(i);
					_progress.setStatus("Performing operation #" + i + " ...");
					try {
						Thread.sleep(100);
					}
					catch (Throwable t) {
					}
				}
				_progress.setDone(true);
			}
		});
		progressThread.start();
	}

	public long now() {
		return System.currentTimeMillis();
	}
	
	public WOActionResults uploadFinished() {
		System.out.println("FileUploadExample.uploadFinished: FINISHED!");
		if (_data != null) {
			System.out.println("FileUploadExample.uploadFinished: Data Size = " + _data.length());
		}
		return null;
	}
}