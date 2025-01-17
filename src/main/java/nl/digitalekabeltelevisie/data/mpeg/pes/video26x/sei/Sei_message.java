/**
 *
 *  http://www.digitalekabeltelevisie.nl/dvb_inspector
 *
 *  This code is Copyright 2009-2020 by Eric Berendsen (e_berendsen@digitalekabeltelevisie.nl)
 *
 *  This file is part of DVB Inspector.
 *
 *  DVB Inspector is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  DVB Inspector is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with DVB Inspector.  If not, see <http://www.gnu.org/licenses/>.
 *
 *  The author requests that he be notified of any application, applet, or
 *  other binary that makes use of this code, but that's more out of curiosity
 *  than anything and is not required.
 *
 */

package nl.digitalekabeltelevisie.data.mpeg.pes.video26x.sei;

import javax.swing.tree.DefaultMutableTreeNode;

import nl.digitalekabeltelevisie.controller.*;
import nl.digitalekabeltelevisie.util.BitSource;

public class Sei_message implements TreeNode{

	// based on 7.3.2.3.1 Supplemental enhancement information message syntax of Rec. ITU-T H.264 (03/2010) – Prepublished version

	int payloadType;
	int last_payload_type_byte;
	int payloadSize;
	int last_payload_size_byte;
	byte[] payload;

	public Sei_message(final BitSource bitSource) {

		payloadType = 0;
		while( bitSource.nextBits(8) == 0xFF ) {
			/* int ff_byte = */ bitSource.f(8);
			payloadType += 255;
		}
		last_payload_type_byte = bitSource.u(8);
		payloadType += last_payload_type_byte;

		payloadSize = 0;
		while( bitSource.nextBits( 8 ) == 0xFF) {
			/* int ff_byte= */ bitSource.f(8); /* equal to 0xFF */
			payloadSize += 255;
		}
		last_payload_size_byte= bitSource.u(8);
		payloadSize += last_payload_size_byte;

		payload= bitSource.readBytes(payloadSize);


	}

	@Override
	public DefaultMutableTreeNode getJTreeNode(final int modus) {
		final DefaultMutableTreeNode s=new DefaultMutableTreeNode(new KVP("Sei_message: "+getPayloadTypeString(payloadType),payloadType,null));
		s.add(new DefaultMutableTreeNode(new KVP("payloadType",payloadType,getPayloadTypeString(payloadType))));
		s.add(new DefaultMutableTreeNode(new KVP("payloadSize",payloadSize,null)));
		s.add(new DefaultMutableTreeNode(new KVP("sei_payload",payload,null)));
		return s;
	}

	public static String getPayloadTypeString(final int payloadType){
		switch (payloadType) {
		case 0: return "buffering_period";
		case 1: return "pic_timing";
		case 2: return "pan_scan_rect";
		case 3: return "filler_payload";
		case 4: return "user_data_registered_itu_t_t35";
		case 5: return "user_data_unregistered";
		case 6: return "recovery_point";
		case 7: return "dec_ref_pic_marking_repetition";
		case 8: return "spare_pic";
		case 9: return "scene_info";
		case 10: return "sub_seq_info";
		case 11: return "sub_seq_layer_characteristics";
		case 12: return "sub_seq_characteristics";
		case 13: return "full_frame_freeze";
		case 14: return "full_frame_freeze_release";
		case 15: return "full_frame_snapshot";
		case 16: return "progressive_refinement_segment_start";
		case 17: return "progressive_refinement_segment_end";
		case 18: return "motion_constrained_slice_group_set";
		case 19: return "film_grain_characteristics";
		case 20: return "deblocking_filter_display_preference";
		case 21: return "stereo_video_info";
		case 22: return "post_filter_hint";
		case 23: return "tone_mapping_info";
		case 24: return "scalability_info";
		case 25: return "sub_pic_scalable_layer";
		case 26: return "non_required_layer_rep";
		case 27: return "priority_layer_info";
		case 28: return "layers_not_present";
		case 29: return "layer_dependency_change";
		case 30: return "scalable_nesting";
		case 31: return "base_layer_temporal_hrd";
		case 32: return "quality_layer_integrity_check";
		case 33: return "redundant_pic_property";
		case 34: return "tl0_dep_rep_index";
		case 35: return "tl_switching_point";
		case 36: return "parallel_decoding_info";
		case 37: return "mvc_scalable_nesting";
		case 38: return "view_scalability_info";
		case 39: return "multiview_scene_info";
		case 40: return "multiview_acquisition_info";
		case 41: return "non_required_view_component";
		case 42: return "view_dependency_change";
		case 43: return "operation_points_not_present";
		case 44: return "base_view_temporal_hrd";
		case 45: return "frame_packing_arrangement";
		case 46: return "multiview_view_position"; /* specified in Annex H Rec. ITU-T H.264 (02/2014)*/
		case 47: return "display_orientation";
		case 48: return "mvcd_scalable_nesting"; /* specified in Annex I Rec. ITU-T H.264 (02/2014)*/
		case 49: return "mvcd_view_scalability_info"; /* specified in Annex I Rec. ITU-T H.264 (02/2014)*/
		case 50: return "depth_representation_info"; /* specified in Annex I */
		case 51: return "three_dimensional_reference_displays_info"; /* specified in Annex I Rec. ITU-T H.264 (02/2014)*/
		case 52: return "depth_timing"; /* specified in Annex I Rec. ITU-T H.264 (02/2014)*/
		case 53: return "depth_sampling_info"; /* specified in Annex I Rec. ITU-T H.264 (02/2014)*/
		case 54: return "constrained_depth_parameter_set_identifier";
		case 56: return "green_metadata";
		case 128: return "structure_of_pictures_info";
		case 129: return "active_parameter_sets";
		case 130: return "decoding_unit_info";
		case 131: return "temporal_sub_layer_zero_index";
		case 132: return "decoded_picture_hash";
		case 133: return "scalable_nesting";
		case 134: return "region_refresh_info";
		case 135: return "no_display";
		case 136: return "time_code";
		case 137: return "mastering_display_colour_volume";
		case 138: return "segmented_rect_frame_packing_arrangement";
		case 139: return "temporal_motion_constrained_tile_sets";
		case 140: return "chroma_resampling_filter_hint";
		case 141: return "knee_function_info";
		case 142: return "colour_remapping_info";
		case 143: return "deinterlaced_field_identification";
		case 144: return "content_light_level_info";
		case 145: return "dependent_rap_indication";
		case 146: return "coded_region_completion";
		case 147: return "alternative_transfer_characteristics";
		case 148: return "ambient_viewing_environment";
		case 149: return "content_colour_volume";
		case 150: return "equirectangular_projection";
		case 151: return "cubemap_projection";
		case 154: return "sphere_rotation";
		case 155: return "regionwise_packing";
		case 156: return "omni_viewport";
		case 157: return "regional_nesting";
		case 158: return "mcts_extraction_info_sets";
		case 159: return "mcts_extraction_info_nesting";
		case 160: return "layers_not_present";
		case 161: return "inter_layer_constrained_tile_sets";
		case 162: return "bsp_nesting";
		case 163: return "bsp_initial_arrival_time";
		case 164: return "sub_bitstream_property";
		case 165: return "alpha_channel_info";
		case 166: return "overlay_info";
		case 167: return "temporal_mv_prediction_constraints";
		case 168: return "frame_field_info";
		case 176: return "three_dimensional_reference_displays_info";
		case 177: return "depth_representation_info";
		case 178: return "multiview_scene_info";
		case 179: return "multiview_acquisition_info";
		case 180: return "multiview_view_position";
		case 181: return "alternative_depth_info";

		// Rec. ITU-T H.265 v7 (11/2019) D.2.1 General SEI message syntax
		case 200: return "sei_manifest";
		case 201: return "sei_prefix_indication";
		case 202: return "annotated_regions";

		// Rec. ITU-T H.266 (04/2022)  D.2.1 General SEI message syntax
		case 203: return "subpic_level_info";
		case 204: return "sample_aspect_ratio_info";
		case 205: return "scalability_dimension_info";
		case 206: return "extended_drap_indication";
		case 207: return "constrained_rasl_encoding_indication";

		default:
			return "reserved_sei_message";
		}

	}

	public int getPayloadType() {
		return payloadType;
	}

	public int getLast_payload_type_byte() {
		return last_payload_type_byte;
	}

	public int getPayloadSize() {
		return payloadSize;
	}

	public int getLast_payload_size_byte() {
		return last_payload_size_byte;
	}


}