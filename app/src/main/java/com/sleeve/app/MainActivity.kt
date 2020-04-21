package com.sleeve.app

import com.bytedance.scene.Scene
import com.sleeve.ui.base.BaseSceneActivity

class MainActivity : BaseSceneActivity() {

    override fun supportRestore(): Boolean {
        return false
    }

    override fun getHomeSceneClass(): Class<out Scene> {
        return MainScene::class.java
    }
}
