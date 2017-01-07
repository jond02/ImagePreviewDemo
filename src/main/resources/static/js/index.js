$(function(){

    let ImageTestApp = (function(){

        'use strict';

        function addNewImages(file){

            let ajaxData = new FormData();
            ajaxData.append('file', file);

            $.ajax({
                url: 'http://localhost:8080/api/v1/createfiles',
                type: "POST",
                data: ajaxData,
                enctype: 'multipart/form-data',
                processData: false,
                contentType: false,
                cache: false,
                success: function () {
                    console.log('success');
                },
                error: function (data) {
                    console.log(data);
                }
            });

        }

        function getExtension(filename){
            return filename.substr(filename.lastIndexOf('.'));
        }

        function checkExtension(ext){
            let exts = ['.tif', '.tiff', '.jpg', '.jpeg'];
            for (let i = 0; i < exts.length; i++){
                if (exts[i].toLowerCase() === ext.toLowerCase()){
                    return true;
                }
            }
            return false;
        }

        //drag and drop
        let sourceContainerId = '';

        let dragStart = function(e){

                $(this).addClass('drag');

                let dto = e.dataTransfer;

                //IE doesn't support text/plain
                try {
                    dto.setData('text/plain', e.target.id);
                } catch (ex){
                    dto.setData('Text', e.target.id);
                }

                sourceContainerId = this.parentElement.id;
            },
            cancel = function(e) {
                //cancel default behaviour
                if (e.preventDefault) {
                    e.preventDefault();
                }

                if (e.stopPropagation) {
                    e.stopPropagation();
                }
                return false;
            },
            dropped = function(e){

                cancel(e);

                let target = $(this);

                $(e.dataTransfer.types).each(function(index, type){

                    if (type === 'Files'){

                        let files = e.dataTransfer.files;

                        $(files).each(function(index, file){

                            //don't add hidden files
                            if (file.name.indexOf('.') !== 0){

                                if (checkExtension(getExtension(file.name))){
                                    //false return will exit each loop
                                    return addNewImages(file);

                                } else {
                                    alert("Error: Only tif and jpg files are supported");
                                    return false;
                                }
                            }

                        });
                    }
                });

                $(this).removeClass('over');
            },
            dragOver = function(e){
                cancel(e);
                e.dataTransfer.dropEffect = 'copy';
                $(this).addClass('over');
            },
            dragLeave = function(e){
                $(this).removeClass('over');
            },
            dragEnd = function(e){
                $('.drag').removeClass('.drag');
                $('.over').removeClass('.over');
            };

        let target = document.querySelector('#target');
        target.addEventListener('drop', dropped, false);
        target.addEventListener('dragenter',cancel, false);
        target.addEventListener('dragover', dragOver, false);
        target.addEventListener('dragleave', dragLeave, false);

        $('#clear').click(function(e){
            e.preventDefault();
            $(target).html('');
        });

    })();


});
