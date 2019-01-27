main () {
    local prefix="this.";

    while getopts ":p:" opt; do
	case $opt in
	    p)
		prefix=$OPTARG >&2
		;;
	esac
    done

    shift $((OPTIND - 1))

    for var in ${@:$(($1+2))}; do
        echo "public $1 get_${var}() {";
	echo "    return $prefix$var;";
	echo "}";
	echo
	echo "public void set_${var}($1 $var) {";
	echo "    $prefix$var = $var;";
	echo "}";
	echo
    done
}

if hash xclip 2>/dev/null; then
    main "$@" | xclip -selection c
else
    main "$@"
fi