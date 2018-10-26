package com.example.maruf.e_pharma.PhysicalTracker.StepCount.StepCount.utils;

import android.content.pm.PackageManager;

import com.example.shuvo.medicare.PhysicalTracker.StepCount.StepCount.Services.AbstractStepDetectorService;
import com.example.shuvo.medicare.PhysicalTracker.StepCount.StepCount.Services.AccelerometerStepDetectorService;
import com.example.shuvo.medicare.PhysicalTracker.StepCount.StepCount.Services.HardwareStepDetectorService;


/**
 * Factory class
 *
 */
public class Factory {

    /**
     * Returns the class of the step detector service which should be used
     *
     * The used step detector service depends on different soft- and hardware preferences.
     * @param pm An instance of the android PackageManager
     * @return The class of step detector
     */
    public static Class<? extends AbstractStepDetectorService> getStepDetectorServiceClass(PackageManager pm){
        if(pm != null && AndroidVersionHelper.supportsStepDetector(pm)) {
            return HardwareStepDetectorService.class;
        }else{
            return AccelerometerStepDetectorService.class;
        }
    }
}
