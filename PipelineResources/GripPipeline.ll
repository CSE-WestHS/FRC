//
// Inputs
//
Inputs
{
	Mat source0;
}

//
// Variables
//
Outputs
{
	Mat hsvThresholdOutput;
	Mat cvErodeOutput;
}

//
// Steps
//

Step HSV_Threshold0
{
    Mat hsvThresholdInput = source0;
    List hsvThresholdHue = [61.510791366906474, 92.45733788395904];
    List hsvThresholdSaturation = [0.0, 255.0];
    List hsvThresholdValue = [4.586330935251798, 178.8481228668942];

    hsvThreshold(hsvThresholdInput, hsvThresholdHue, hsvThresholdSaturation, hsvThresholdValue, hsvThresholdOutput);
}

Step CV_erode0
{
    Mat cvErodeSrc = hsvThresholdOutput;
    Mat cvErodeKernel;
    Point cvErodeAnchor = (-1, -1);
    Double cvErodeIterations = 2.0;
    BorderType cvErodeBordertype = BORDER_CONSTANT;
    Scalar cvErodeBordervalue = (-1);

    cvErode(cvErodeSrc, cvErodeKernel, cvErodeAnchor, cvErodeIterations, cvErodeBordertype, cvErodeBordervalue, cvErodeOutput);
}




