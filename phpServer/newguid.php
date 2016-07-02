<?php
function NewGuid() {
    if (function_exists('com_create_guid')){
        /**
         * @var Guid $uuid
         */
        $uuid = com_create_guid();
        print "com created";
        return $uuid;
    }
    else {
        mt_srand((double)microtime()*10000);
        $charid = strtoupper(md5(uniqid(rand(), true)));
        $hyphen = chr(45);
        /**
         * @var Guid $uuid
         */
        $uuid =  substr($charid, 0, 8).$hyphen
                .substr($charid, 8, 4).$hyphen
                .substr($charid,12, 4).$hyphen
                .substr($charid,16, 4).$hyphen
                .substr($charid,20,12);
        return $uuid;
    }
}
?>