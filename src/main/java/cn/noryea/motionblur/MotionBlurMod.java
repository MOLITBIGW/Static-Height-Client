package cn.noryea.motionblur;

import cn.noryea.motionblur.config.SimpleConfig;
import net.fabricmc.api.ModInitializer;

public final class MotionBlurMod implements ModInitializer {

    @Override
    public void onInitialize() {
        new SimpleConfig();
    }
}

