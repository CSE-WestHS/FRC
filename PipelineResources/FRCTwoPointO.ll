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
	ContoursReport findContoursOutput;
	ContoursReport filterContoursOutput;
}

//
// Steps
//

Step HSV_Threshold0
{
    Mat hsvThresholdInput = source0;
    List hsvThresholdHue = [50.17985611510791, 104.486301369863];
    List hsvThresholdSaturation = [114.65827338129498, 255.0];
    List hsvThresholdValue = [50.44964028776978, 255.0];

    hsvThreshold(hsvThresholdInput, hsvThresholdHue, hsvThresholdSaturation, hsvThresholdValue, hsvThresholdOutput);
}

Step Find_Contours0
{
    Mat findContoursInput = hsvThresholdOutput;
    Boolean findContoursExternalOnly = false;

    findContours(findContoursInput, findContoursExternalOnly, findContoursOutput);
}

Step Filter_Contours0
{
    ContoursReport filterContoursContours = findContoursOutput;
    Double filterContoursMinArea = 1.0;
    Double filterContoursMinPerimeter = 10.0;
    Double filterContoursMinWidth = 5.0;
    Double filterContoursMaxWidth = 1000;
    Double filterContoursMinHeight = 5.0;
    Double filterContoursMaxHeight = 1000;
    List filterContoursSolidity = [25.179856115107913, 100];
    Double filterContoursMaxVertices = 1000000;
    Double filterContoursMinVertices = 0;
    Double filterContoursMinRatio = 0;
    Double filterContoursMaxRatio = 1000;

    filterContours(filterContoursContours, filterContoursMinArea, filterContoursMinPerimeter, filterContoursMinWidth, filterContoursMaxWidth, filterContoursMinHeight, filterContoursMaxHeight, filterContoursSolidity, filterContoursMaxVertices, filterContoursMinVertices, filterContoursMinRatio, filterContoursMaxRatio, filterContoursOutput);
}




