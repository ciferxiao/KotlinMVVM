package com.ggh.baselibrary

import android.os.Environment

object MyConfig {
    var bugly_id = "3b5693a229"

    var phone = "phone"
    var userName = "userName"
    const val SP_MY_NICKNAME = "sp_my_nickname"
    const val SP_MY_UID = "sp_my_uid"
    const val SP_LEAP_ID = "sp_leap_id"
    const val SP_ACCESS_TOKEN = "sp_access_token"
    const val SP_COMMUNI_CHANNEL = "sp_communit_channel3" //sp_communit_channel2

    const val SP_COMMUNI_CHANNEL_OPEN = "sp_communi_channel_open"
    const val SP_COMMUNI_PTT_OPEN = "sp_communi_ptt_open"
    const val SP_ME_IMG = "SP_me_img"


    const val CAMERA_BACK = "0"
    const val CAMERA_FRONT = "1"

    /**
     * 默认中文
     */
    const val SP_LANGUAGE = "sp_language_zh"
    const val SP_IS_LOGIN = "my_is_login"
    const val SP_NICKNAME_UPDATE_TIME = "nickname_update_time"
    const val SP_WORK_SUMMARY = "SP_WORK_SUMMARY"
    const val SP_REVERSE_MEMBER_UPDATE_TIME = "sp_reverse_member_update_time"
    const val SP_CLOUD_MEMBER_UPDATE_TIME = "SP_CLOUD_MEMBER_UPDATE_TIME"

    var sp_ble_name = "sp_ble_name"
    const val SP_UNREAD_ID = "sp_unread_id"

    var ota_product_app = "LeapCollect"
    var ota_product_mcu = "LeapCollect-mcu"
    var ota_product_ble = "LeapCollect-ble"

    var path =
        Environment.getExternalStorageDirectory().absolutePath + "/" + "collect"
    var record_dir = "$path/media/video/"
    var record_ffm_dir = "$path/media/ffmpeg/"
    var pic_dir = "$path/media/pic/"
    var audio_dir = "$path/media/audio/"
    var down_dir = "$path/media/down/"
    var ota_dir = "$path/ota/"
    var log_dir = "$path/log1/"
    var map_dir = "$path/map/"
    var me_dir = "$path/media/me/"

    var me_img = "me.jpg"
    var ota_apk_name = "LeapCollect.apk"
    var ota_ble_name1 = "h1_crc_ota.bin"
    var ota_ble_name2 = "h2_crc_ota.bin"
    var ota_mcu_name = "mcu.ota"


    var pic_count = 5
    var client_id = "44E7F9DC2DE558BFBC5D808E38267001"
    var client_secret = "F851C75F9F55610C2C321F1AC167BAAB"

    var reverse_id = "reverse_id"
    var sp_form_id = "sp_form_id"
    var sp_form_value = "sp_form_value"
    var sp_camera_setting = "sp_camera_setting"
    var sp_work_summary = "sp_work_summary"
    var sp_map_device = "sp_map_device"
    var SP_reverse_id = "SP_reverse_id"
    var SP_reverse_size = "SP_reverse_size"


    var form_input_max = 128
    var form_textare_max = 1024

    var sp_autho = "sp_autho"


}